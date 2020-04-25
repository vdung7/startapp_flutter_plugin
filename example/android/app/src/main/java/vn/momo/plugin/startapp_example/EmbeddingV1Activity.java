package vn.momo.plugin.startapp_example;

import android.os.Bundle;
import android.view.View;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import vn.momo.plugin.startapp.StartAppBannerPlugin;

public class EmbeddingV1Activity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StartAppBannerPlugin.registerWith(registrarFor("vn.momo.plugin.startapp.StartAppBannerPlugin"));
  }
}
