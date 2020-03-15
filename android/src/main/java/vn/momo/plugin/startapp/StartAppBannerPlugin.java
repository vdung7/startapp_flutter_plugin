package vn.momo.plugin.startapp;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AutoInterstitialPreferences;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import android.content.pm.PackageItemInfo;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * @author dungvu
 * @since 2019-06-04
 */
public class StartAppBannerPlugin {
    public static final String PLUGIN_KEY = "vn.momo.plugin.startapp.StartAppBannerPlugin";
    private static final String STARTAPP_ID_KEY = "vn.momo.plugin.startapp.STARTAPP_ID";
    private static final String DEFAULT_STARTAPP_ID = "no-startapp-id";

    private static Activity mainActivity;
    private static StartAppAd startAppAd;

    public static void registerWith(Registrar registrar) {
        String startAppId = DEFAULT_STARTAPP_ID;
        try {
            ApplicationInfo ai = registrar.context().getPackageManager().getApplicationInfo(
                    registrar.context().getPackageName(),
                    PackageManager.GET_META_DATA
            );
            Bundle bundle = ai.metaData;
            startAppId = bundle.getString(STARTAPP_ID_KEY, DEFAULT_STARTAPP_ID);
        } catch (PackageManager.NameNotFoundException ignored) {}

        Log.d("StartAppBannerPlugin", "STARTAPP ID = " + startAppId);
        StartAppSDK.init(registrar.activity(), startAppId, false);
        StartAppAd.disableSplash();

        mainActivity = registrar.activity();
        startAppAd = new StartAppAd(registrar.context());

        registrar.platformViewRegistry()
                .registerViewFactory(
                        PLUGIN_KEY, new BannerFactory(registrar.messenger()));

        new MethodChannel(registrar.view(), "flutter_startapp").setMethodCallHandler(
                (call, result) -> {
                    switch (call.method) {
                        case "showAd":
                            StartAppAd.showAd(mainActivity);
                            result.success(null);
                            break;
                        case "showRewardedAd":
                            startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                                @Override
                                public void onReceiveAd(Ad ad) {
                                    startAppAd.showAd();
                                }

                                @Override
                                public void onFailedToReceiveAd(Ad arg0) {
                                    Log.e("StartAppPlugin", "Failed to load rewarded video with reason: " + arg0.getErrorMessage());
                                }
                            });
                            result.success(null);
                            break;
                        default:
                            result.notImplemented();
                    }
                });
    }

    public static Activity activity() {
        return mainActivity;
    }
}
