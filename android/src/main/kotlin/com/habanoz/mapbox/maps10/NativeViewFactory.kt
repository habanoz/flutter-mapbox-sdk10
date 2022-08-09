 package com.habanoz.mapbox.maps10

import android.content.Context
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.extension.style.terrain.generated.terrain
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

 class NativeViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    private var mapViewRef: MapView? = null

    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        val created =  NativeView(context!!, viewId, creationParams)
        mapViewRef = created.view as MapView?

        return created
    }

    fun loadStyleURI(uri: String): String{
        mapViewRef?.getMapboxMap()?.loadStyleUri(uri)
        return "Done"
    }

    fun loadStyle(uri: String): String{
        mapViewRef?.getMapboxMap()?.loadStyle(styleExtension = style(uri){
            +rasterDemSource("TERRAIN_SOURCE") {
                url("mapbox://mapbox.mapbox-terrain-dem-v1")
            }
            +terrain("TERRAIN_SOURCE") {
                exaggeration(1.1)
            }
        })
        return "Done"
    }
}