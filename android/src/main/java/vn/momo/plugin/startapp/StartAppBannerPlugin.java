package vn.momo.plugin.startapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import android.content.pm.PackageItemInfo;

import io.flutter.plugin.common.PluginRegistry;

/**
 * @author dungvu
 * @since 2019-06-04
 */
public class StartAppBannerPlugin {
    private static Banner BANNER;
    public static final String PLUGIN_KEY = "vn.momo.plugin.startapp.StartAppBannerPlugin";

    public static void registerWith(PluginRegistry.Registrar registrar) {
        String startAppId = "no-startapp-id";
        try {
            ApplicationInfo ai = registrar.context().getPackageManager().getApplicationInfo(
                    registrar.context().getPackageName(),
                    PackageManager.GET_META_DATA
            );
            Bundle bundle = ai.metaData;
            startAppId = bundle.getString("vn.momo.plugin.startapp.STARTAPP_ID", "no-startapp-id");
        } catch (PackageManager.NameNotFoundException ignored) {}

        Log.d("StartAppBannerPlugin", "STARTAPP ID = " + startAppId);
        StartAppSDK.init(registrar.activity(), startAppId, false);
        StartAppAd.disableSplash();

        Banner banner = new Banner(registrar.activity(), new BannerListener() {
            @Override
            public void onReceiveAd(View banner) {
                BANNER = (Banner) banner;
            }

            @Override
            public void onFailedToReceiveAd(View banner) {
            }

            @Override
            public void onClick(View banner) {
            }
        });
        banner.loadAd(300, 50);

        registrar.platformViewRegistry()
                .registerViewFactory(
                        PLUGIN_KEY, new BannerFactory(registrar.messenger()));
    }

    public static Banner getBanner() {
        return BANNER;
    }
}
