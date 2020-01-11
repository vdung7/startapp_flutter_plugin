# Flutter - StartApp SDK Plugin

Flutter plugin for StartApp SDK.

Currently, this plugin only support Android platform. The plugin is my weekend project, so any contributes to make it
better are welcome.

## How to use the plugin 

* Add StartApp ID of project to AndroidManifest.xml as `meta-data`:
```xml
<meta-data android:name="vn.momo.plugin.startapp.STARTAPP_ID" android:value="\ {your-startapp-id}" />
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
