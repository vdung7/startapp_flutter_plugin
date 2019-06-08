package vn.momo.plugin.startapp;

import android.view.View;

import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import io.flutter.plugin.common.PluginRegistry;

/**
 * @author dungvu
 * @since 2019-06-04
 */
public class StartAppBannerPlugin {
    private static Banner BANNER;
    public static final String PLUGIN_KEY = "vn.momo.plugin.startapp.StartAppBannerPlugin";

    public static void registerWith(PluginRegistry.Registrar registrar) {
        StartAppSDK.init(registrar.activity(), "205308886", false);
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
