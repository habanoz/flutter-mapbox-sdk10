import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'maps10_method_channel.dart';

abstract class Maps10Platform extends PlatformInterface {
  /// Constructs a Maps10Platform.
  Maps10Platform() : super(token: _token);

  static final Object _token = Object();

  static Maps10Platform _instance = MethodChannelMaps10();

  /// The default instance of [Maps10Platform] to use.
  ///
  /// Defaults to [MethodChannelMaps10].
  static Maps10Platform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [Maps10Platform] when
  /// they register themselves.
  static set instance(Maps10Platform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> loadStyleUri(String uri) async {
    throw UnimplementedError('loadStyleUri() has not been implemented.');
  }

  Future<String?> loadStyleStreet() async {
    throw UnimplementedError('loadStyleStreet() has not been implemented.');
  }

  Future<String?> loadStyleOutdoor() async {
    throw UnimplementedError('loadStyleOutdoor() has not been implemented.');
  }

  Future<String?> loadStyleSatellite() async {
    throw UnimplementedError('loadStyleSatellite() has not been implemented.');
  }
}
