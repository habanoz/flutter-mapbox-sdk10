import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:maps10/maps10_method_channel.dart';

void main() {
  MethodChannelMaps10 platform = MethodChannelMaps10();
  const MethodChannel channel = MethodChannel('maps10');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
