package com.habanoz.mapbox.maps10

import android.content.Context
import android.graphics.Color
import android.view.View
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.extension.style.terrain.generated.terrain
import io.flutter.plugin.platform.PlatformView

internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) :
    PlatformView {
    private val mapView: MapView

    override fun getView(): View {
        return mapView
    }

    override fun dispose() {}

    init {
        mapView = MapView(context, getMapOptions(context))
        mapView.getMapboxMap().loadStyle(styleExtension = style(Style.SATELLITE_STREETS) {
            +rasterDemSource("TERRAIN_SOURCE") {
                url("mapbox://mapbox.mapbox-terrain-dem-v1")
            }
            +terrain("TERRAIN_SOURCE") {
                exaggeration(1.1)
            }
        }
        )
        mapView.setBackgroundColor(Color.rgb(255, 100, 100))
    }

    private fun getMapOptions(context: Context): MapInitOptions {
        // set map options
        val mapOptions = MapOptions.Builder().applyDefaultParams(context)
            .constrainMode(ConstrainMode.HEIGHT_ONLY)
            .glyphsRasterizationOptions(
                GlyphsRasterizationOptions.Builder()
                    .rasterizationMode(GlyphsRasterizationMode.IDEOGRAPHS_RASTERIZED_LOCALLY)
                    // Font family is required when the GlyphsRasterizationMode is set to IDEOGRAPHS_RASTERIZED_LOCALLY or ALL_GLYPHS_RASTERIZED_LOCALLY
                    .fontFamily("sans-serif")
                    .build()
            )
            .build()

        
        val resourceOptions = ResourceOptions.Builder().applyDefaultParams(context)
            .accessToken(context.getString(R.string.mapbox_access_token))
            .tileStoreUsageMode(TileStoreUsageMode.DISABLED)
            .build();


        // set initial camera position
        val initialCameraOptions = CameraOptions.Builder()
            .center(Point.fromLngLat(37.1, 37.2))
            .zoom(9.0)
            .bearing(45.0)
            .pitch(45.0)
            .build()

        val mapInitOptions = MapInitOptions(
            context,
            resourceOptions,
            mapOptions,
            MapInitOptions.defaultPluginList,
            initialCameraOptions,
            true
        )

        return mapInitOptions
    }
}