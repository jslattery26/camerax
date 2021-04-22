import 'dart:async';

import 'package:flutter/services.dart';

export 'camerapreview.dart';

class Camerax {
  static const MethodChannel _channel = const MethodChannel('camerax');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> get openCamera async {
    await _channel.invokeMethod('openCamera');
  }
}
