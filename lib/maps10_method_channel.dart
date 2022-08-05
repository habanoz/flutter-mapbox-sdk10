import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'maps10_platform_interface.dart';

/// An implementation of [Maps10Platform] that uses method channels.
class MethodChannelMaps10 extends Maps10Platform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('maps10');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> loadStyleUri(String uri) async {
    final version =
        await methodChannel.invokeMethod<String>('loadStyleUri', [uri]);
    return version;
  }

  @override
  Future<String?> loadStyleStreet() async {
    final version = await methodChannel.invokeMethod<String>('loadStyleStreet');
    return version;
  }

  @override
  Future<String?> loadStyleOutdoor() async {
    final version =
        await methodChannel.invokeMethod<String>('loadStyleOutdoor');
    return version;
  }

  @override
  Future<String?> loadStyleSatellite() async {
    final version =
        await methodChannel.invokeMethod<String>('loadStyleSatellite');
    return version;
  }
}
