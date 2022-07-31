import 'maps10_platform_interface.dart';

class Maps10 {
  Future<String?> getPlatformVersion() {
    return Maps10Platform.instance.getPlatformVersion();
  }

  Future<String?> loadStyleOutdoor() {
    return Maps10Platform.instance.loadStyleOutdoor();
  }

  Future<String?> loadStyleSatellite() {
    return Maps10Platform.instance.loadStyleSatellite();
  }

  Future<String?> loadStyleStreet() {
    return Maps10Platform.instance.loadStyleStreet();
  }
}
