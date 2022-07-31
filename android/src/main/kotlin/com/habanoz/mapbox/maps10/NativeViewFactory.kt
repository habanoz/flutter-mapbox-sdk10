package com.habanoz.mapbox.maps10

import android.content.Context
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import com.mapbox.maps.Style
import com.mapbox.maps.*

class NativeViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    private var mapViewRef: MapView? = null

    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        val created =  NativeView(context!!, viewId, creationParams)
        mapViewRef = created.getView() as MapView?

        return created
    }

    fun loadStyleURI(uri: String): String{
        mapViewRef?.getMapboxMap()?.loadStyleUri(uri)
        return "Done"
    }
}