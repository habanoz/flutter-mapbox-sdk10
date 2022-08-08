package com.habanoz.mapbox.maps10_example

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentManager
import io.flutter.Log
import io.flutter.embedding.android.FlutterFragment


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    companion object {
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
    }

    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController: WindowInsetsControllerCompat? =
            ViewCompat.getWindowInsetsController(window.decorView)
       windowInsetsController?.isAppearanceLightNavigationBars = true
       windowInsetsController?.isAppearanceLightStatusBars = true

       /* if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }*/

        setContentView(R.layout.my_activity_layout)

        val fragmentManager: FragmentManager = supportFragmentManager

        flutterFragment =
            fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment?

        if (flutterFragment == null) {
            // var newFlutterFragment = FlutterFragment.createDefault()
            val newFlutterFragment: FlutterFragment =
                FlutterFragment.withCachedEngine("my_engine_id").build();
            flutterFragment = newFlutterFragment
            fragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container,
                    newFlutterFragment,
                    TAG_FLUTTER_FRAGMENT
                )
                .commit()
        }
    }

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

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment!!.onPostResume()
    }

    override fun onNewIntent(@NonNull intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment!!.onNewIntent(intent)
    }

    override fun onBackPressed() {
        flutterFragment!!.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        flutterFragment!!.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onUserLeaveHint() {
        flutterFragment!!.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment!!.onTrimMemory(level)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}
