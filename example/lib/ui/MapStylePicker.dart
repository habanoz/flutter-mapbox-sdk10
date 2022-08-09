import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:maps10/maps10.dart';

@immutable
class StylePickerFab extends StatefulWidget {
  const StylePickerFab({super.key, required this.platform});
  final Maps10 platform;

  @override
  State<StylePickerFab> createState() => _StylePickerFabState(platform);
}

class _StylePickerFabState extends State<StylePickerFab>
    with SingleTickerProviderStateMixin {
  int _selected = 0;
  final Maps10 platform;

  final double _kItemExtent = 32.0;
  final List<String> _styleNames = <String>[
    'Satellite',
    'Outdoors',
    'LIGHT',
    'DARK',
    'Streets',
    'Satellite Streets',
  ];

  final List<String> _styleUris = <String>[
    'mapbox://styles/mapbox/satellite-v9',
    'mapbox://styles/mapbox/outdoors-v11',
    'mapbox://styles/mapbox/light-v10',
    'mapbox://styles/mapbox/dark-v10',
    'mapbox://styles/mapbox/streets-v11',
    'mapbox://styles/mapbox/satellite-streets-v11',
  ];

  _StylePickerFabState(this.platform);

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(
      onPressed: () => _showDialog(
        CupertinoPicker(
          magnification: 1.22,
          squeeze: 1.2,
          useMagnifier: true,
          itemExtent: _kItemExtent,
          scrollController: FixedExtentScrollController(initialItem: _selected),
          // This is called when selected item is changed.
          onSelectedItemChanged: (int selectedItem) {
            setState(() {
              _selected = selectedItem;
            });

            platform.loadStyleUri(_styleUris[selectedItem]);
          },
          children: List<Widget>.generate(_styleNames.length, (int index) {
            return Center(
              child: Text(
                _styleNames[index],
              ),
            );
          }),
        ),
      ),
      backgroundColor: Colors.green,
      child: const Icon(Icons.layers),
    );
  }

  @override
  Widget build2(BuildContext context) {
    return CupertinoPageScaffold(
      child: DefaultTextStyle(
        style: TextStyle(
          color: CupertinoColors.label.resolveFrom(context),
          fontSize: 22.0,
        ),
        child: Center(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('Selected fruit: '),
              CupertinoButton(
                padding: EdgeInsets.zero,
                // Display a CupertinoPicker with list of fruits.
                onPressed: () => _showDialog(
                  CupertinoPicker(
                    magnification: 1.22,
                    squeeze: 1.2,
                    useMagnifier: true,
                    itemExtent: _kItemExtent,
                    // This is called when selected item is changed.
                    onSelectedItemChanged: (int selectedItem) {
                      setState(() {
                        _selected = selectedItem;
                      });
                    },
                    children:
                        List<Widget>.generate(_styleNames.length, (int index) {
                      return Center(
                        child: Text(
                          _styleNames[index],
                        ),
                      );
                    }),
                  ),
                ),
                // This displays the selected fruit name.
                child: Text(
                  _styleNames[_selected],
                  style: const TextStyle(
                    fontSize: 22.0,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _showDialog(Widget child) {
    showCupertinoModalPopup<void>(
        context: context,
        builder: (BuildContext context) => Container(
              height: 216,
              padding: const EdgeInsets.only(top: 6.0),
              // The Bottom margin is provided to align the popup above the system navigation bar.
              margin: EdgeInsets.only(
                bottom: MediaQuery.of(context).viewInsets.bottom,
              ),
              // Provide a background color for the popup.
              color: CupertinoColors.systemBackground.resolveFrom(context),
              // Use a SafeArea widget to avoid system overlaps.
              child: SafeArea(
                top: false,
                child: child,
              ),
            ));
  }
}
