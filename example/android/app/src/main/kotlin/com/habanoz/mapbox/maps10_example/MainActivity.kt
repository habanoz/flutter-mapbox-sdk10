package com.habanoz.mapbox.maps10_example

import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {
    private val TAG = "MainActivity"

    override fun onStart() {
        print("MainActivity s")
        super.onStart()


        Log.e(TAG, "log onStart completed");
    }

    override fun onStop() {
        print("MainActivity s")

        super.onStop()

        Log.e(TAG, "log onStop completed");
    }

    override fun onDestroy() {
        print("MainActivity s")

        super.onDestroy()

        Log.e(TAG, "log onDestroy completed");
    }

    override fun onLowMemory() {
        print("MainActivity s")

        super.onLowMemory()

        Log.e(TAG, "log onLowMemory completed");
    }
}
