# Flutter Plugin for Mapbox SDK 10

A Flutter plugin project for mapbox sdk version 10.

Currently only adroid platform is supported.

Full functionality support is not planned.

# How to start

Mapbox personel tokens are required to build and run.

## Providing download token

Provide the download token by adding it to global gradle.properties file with key name MAPBOX_DOWNLOADS_TOKEN.

## Providing mapbox access token

Create developer-config.xml file under 'android/src/main/res/values' directory.

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="mapbox_access_token">your access token</string>
</resources>
```

developer-config.xml is included in .gitignore file to avoid exposing access token to public.

