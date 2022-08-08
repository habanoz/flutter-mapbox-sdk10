package com.habanoz.mapbox.maps10

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.habanoz.mapbox.maps10.util.CampatUtils
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.extension.style.terrain.generated.terrain
import com.mapbox.maps.plugin.Plugin
import com.mapbox.maps.plugin.compass.CompassPlugin
import com.mapbox.maps.plugin.scalebar.ScaleBarPlugin
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

        mapView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(p0: View?) {
                val defaultMarginTop = ViewCompat.getRootWindowInsets(p0!!)?.getInsets(WindowInsetsCompat.Type.systemBars())?.top?.toFloat()!!

                val scaleBarPlugin = mapView.getPlugin<ScaleBarPlugin>(Plugin.MAPBOX_SCALEBAR_PLUGIN_ID)
                scaleBarPlugin!!.marginTop = defaultMarginTop
                scaleBarPlugin!!.position = Gravity.TOP

                val compassPlugin = mapView.getPlugin<CompassPlugin>(Plugin.MAPBOX_COMPASS_PLUGIN_ID)
                compassPlugin!!.marginTop = defaultMarginTop
                compassPlugin!!.fadeWhenFacingNorth = true
            }

            override fun onViewDetachedFromWindow(p0: View?) {
                TODO("Not yet implemented")
            }
        })

    /*    mapView.setOnApplyWindowInsetsListener { view, insets ->
            Log.w("nativeView", "setOnApplyWindowInsetsListener called")
            val height = CampatUtils.getStatusBarHeight(insets).toFloat()
            scaleBarPlugin.marginTop = height
            compassPlugin.marginTop = height

            Log.w("nativeView", "setOnApplyWindowInsetsListener Height %f".format(height))

            insets
        }*/

        ViewCompat.setOnApplyWindowInsetsListener(view) { view, windowInsets ->
            val insets = windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())
            // Apply the insets as a margin to the view. Here the system is setting
            // only the bottom, left, and right dimensions, but apply whichever insets are
            // appropriate to your layout. You can also update the view padding
            // if that's more appropriate.

            /*view.updateLayoutParams<ViewGroup.MarginLayoutParams>(
                leftMargin = insets.left,
                bottomMargin = insets.bottom,
                rightMargin = insets.right,
            )*/

            Log.w("nativeView", "ViewCompat.setOnApplyWindowInsetsListener Height %d".format(insets.top))

            val scaleBarPlugin = mapView.getPlugin<ScaleBarPlugin>(Plugin.MAPBOX_SCALEBAR_PLUGIN_ID)
            val compassPlugin = mapView.getPlugin<CompassPlugin>(Plugin.MAPBOX_COMPASS_PLUGIN_ID)

            scaleBarPlugin?.marginTop = insets.top.toFloat()
            compassPlugin?.marginTop = insets.top.toFloat()

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }

        /*ViewCompat.setOnApplyWindowInsetsListener(mapView
        ) { v, insets ->
            val height = CampatUtils.getStatusBarHeightCompat(insets).toFloat()
            scaleBarPlugin.marginTop = height
            compassPlugin.marginTop = height

            Log.w("nativeView","ViewCompat.setOnApplyWindowInsetsListener Height %f".format(height))

            insets!!
        }
*/

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