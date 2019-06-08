package vn.momo.plugin.startapp;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

/**
 * @author dungvu
 * @since 2019-06-04
 */
public class FlutterBannerView implements PlatformView, MethodChannel.MethodCallHandler {
    private final FrameLayout bannerContainer;

    FlutterBannerView(Context context, BinaryMessenger messenger, int id) {
        bannerContainer = new FrameLayout(context);
        new MethodChannel(messenger, StartAppBannerPlugin.PLUGIN_KEY + "_" + id)
                .setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case "loadAd":
                while (StartAppBannerPlugin.getBanner() == null) {}
                bannerContainer.removeAllViews();
                bannerContainer.addView(StartAppBannerPlugin.getBanner());
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public View getView() {
        return bannerContainer;
    }

    @Override
    public void dispose() {
        bannerContainer.removeAllViews();
    }

}
