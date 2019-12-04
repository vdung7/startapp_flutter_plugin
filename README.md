# startapp

Flutter plugin for StartApp SDK

## How to use the plugin 

* Add StartApp ID of project to AndroidManifest.xml as `meta-data`:
```
<meta-data android:name="vn.momo.plugin.startapp.STARTAPP_ID" android:value="\ {your-startapp-id}" />
```

* Banner as widget
```
import 'package:flutter/material.dart';
import 'package:startapp/startapp.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyAppState();
}

class MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "StartApp Example",
        home: Scaffold(
            appBar: AppBar(title: const Text('StartApp Example')),
            body: Center(
                child: Column(
                  children: <Widget>[
                    Text('Banner sample'),

                    // StartApp AdBanner widget
                    AdBanner(),
                  ],
                )
            )
        )
    );
  }
}
```
* Load interstitial ad
```
  static showInterstitialAd() async {
    if (!App.isInDebugMode) {
      await platform.invokeMethod('showAd');
    }
  }
```
