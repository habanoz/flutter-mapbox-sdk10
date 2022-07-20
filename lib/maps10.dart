
import 'maps10_platform_interface.dart';

class Maps10 {
  Future<String?> getPlatformVersion() {
    return Maps10Platform.instance.getPlatformVersion();
  }
}
