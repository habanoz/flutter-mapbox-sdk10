package com.habanoz.mapbox.maps10

import android.app.Activity
import androidx.annotation.NonNull
import com.mapbox.maps.ResourceOptionsManager
import com.mapbox.maps.TileStoreUsageMode
import io.flutter.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import com.mapbox.maps.Style

/** Maps10Plugin */
class Maps10Plugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private val TAG = "Maps10Plugin"

    private var activity: Activity? = null
    private var nativeViewFactory = NativeViewFactory()

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "maps10")
        channel.setMethodCallHandler(this)

        val context = flutterPluginBinding.applicationContext
        ResourceOptionsManager.getDefault(context, context.getString(R.string.mapbox_access_token))
            .update {
                tileStoreUsageMode(TileStoreUsageMode.READ_ONLY)
            }

        flutterPluginBinding.platformViewRegistry.registerViewFactory("maps10", nativeViewFactory)

        print("onAttachedToEngine completed")
        Log.e(TAG, "log onAttachedToEngine completed");
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "loadStyleStreet") {
            nativeViewFactory.loadStyleURI(Style.MAPBOX_STREETS)
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        }  else if (call.method == "loadStyleOutdoor") {
            nativeViewFactory.loadStyleURI(Style.OUTDOORS)
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        }  else if (call.method == "loadStyleSatellite") {
            nativeViewFactory.loadStyleURI(Style.SATELLITE)
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } 
        else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        print("onDetachedFromEngine completed")
        Log.e(TAG, "log onAttachedToEngine completed");
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        print("attached to activity")

        activity = binding.activity;
        Log.e(TAG, "log onAttachedToActivity completed");

    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivity() {
        print("detached from activity")

        activity = null;

        Log.e(TAG, "log onDetachedFromActivity completed");
    }
}
