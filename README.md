# Flutter - StartApp SDK Plugin

Flutter plugin for StartApp SDK.

Currently, this plugin only support Android platform. The plugin is my weekend project, so any contributes to make it
better are welcome.

## How to use the plugin 

* Updating AndroidManifest.xml file

_Base on [StartApp SDK document](https://support.startapp.com/hc/en-us/articles/360002411114-Android-Standard-#Step1,AddingandInitializingtheSDKtoYourProject)_

Add following meta-data tag with your StartApp App Id under the <application> section in your manifest file:
```xml
<meta-data android:name="com.startapp.sdk.APPLICATION_ID" android:value="startapp_app_id" />
```

If you want to disable return ads (they are enabled by default) please add following meta-data tag:
```xml
<meta-data android:name="com.startapp.sdk.RETURN_ADS_ENABLED" android:value="false" />
```

If you want to disable splash ads (they are enabled by default) please add following meta-data tag:
```xml
<meta-data android:name="vn.momo.plugin.startapp.SPLASH_AD_ENABLED" android:value="false" />
```

* Banner as widget
```dart
import 'package:startapp/startapp.dart';
...

// StartApp AdBanner as widget
AdBanner(),
```

* Load interstitial ad
```dart
import 'package:startapp/startapp.dart';
...
await StartApp.showInterstitialAd();
```

* Load rewarded video ad
```dart
import 'package:startapp/startapp.dart';
...
await StartApp.showRewardedAd(onVideoCompleted: () {
    // video completed callback
}, onReceiveAd: () {
    // ad received callback
}, onFailedToReceiveAd: (String error) {
    // failed to received ad callback
});
```

## Support
If this package was helpful to you in delivering on your project or you just wanna to support this project, a cup of coffee would be highly appreciated ;-)

[![Buy me a coffee](https://cdn.buymeacoffee.com/buttons/default-green.png)](https://www.buymeacoffee.com/htoOyQV)
