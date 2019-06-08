import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void BannerCreatedCallback(BannerController controller);
const String PLUGIN_KEY = "vn.momo.plugin.startapp.StartAppBannerPlugin";

class AdBanner extends StatefulWidget {
  const AdBanner({
    Key key,
    this.onCreated,
  }) : super(key: key);

  final BannerCreatedCallback onCreated;

  @override
  State<AdBanner> createState() => _BannerState();
}

class _BannerState extends State<AdBanner> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return Container(
          width: 300.0,
          height: 100.0,
          child: AndroidView(
            viewType: PLUGIN_KEY,
            onPlatformViewCreated: _onPlatformViewCreated,
          )
      );
    }
    return Text('$defaultTargetPlatform is no need showing ads');
  }

  void _onPlatformViewCreated(int id) {
    BannerController controller = new BannerController._(id);
    controller.loadAd();
    if (widget.onCreated == null) {
      return;
    }
    widget.onCreated(controller);
  }
}

class BannerController {
  BannerController._(int id)
      : _channel = new MethodChannel('${PLUGIN_KEY}_$id');

  final MethodChannel _channel;

  Future<void> loadAd() async {
    return _channel.invokeMethod('loadAd');
  }
}