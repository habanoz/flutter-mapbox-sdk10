package com.habanoz.mapbox.maps10_example

import android.app.Application
import android.util.Log
import android.view.OrientationEventListener
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


class FlutterApplication : Application() {
    lateinit var flutterEngine: FlutterEngine

    override fun onCreate() {
        super.onCreate()

        flutterEngine = FlutterEngine(this)

        flutterEngine.navigationChannel.setInitialRoute("/");

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)


        val mOrientationListener: OrientationEventListener = object : OrientationEventListener(
            applicationContext
        ) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == 0 || orientation == 180) {
                    Log.d("FlutterApplication", "orientation portrait")
                } else if (orientation == 90 || orientation == 270) {
                    Log.d("FlutterApplication", "orientation landscape")
                }
            }
        }

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable()
        }

    }
}