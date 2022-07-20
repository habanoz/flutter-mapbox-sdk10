import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';

import 'package:maps10/maps10.dart';
import 'package:maps10/maps10_platform_interface.dart';
import 'package:maps10/maps10_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMaps10Platform
    with MockPlatformInterfaceMixin
    implements Maps10Platform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final Maps10Platform initialPlatform = Maps10Platform.instance;

  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  test('$MethodChannelMaps10 is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMaps10>());
  });

  test('getPlatformVersion', () async {
    Maps10 maps10Plugin = Maps10();
    MockMaps10Platform fakePlatform = MockMaps10Platform();
    Maps10Platform.instance = fakePlatform;

    expect(await maps10Plugin.getPlatformVersion(), '42');
  });

  testWidgets('Can get battery level', (tester) async {
    Maps10 maps10Plugin = Maps10();
    MockMaps10Platform fakePlatform = MockMaps10Platform();
    Maps10Platform.instance = fakePlatform;

    expect(await maps10Plugin.getPlatformVersion(), '42');
  });
}
