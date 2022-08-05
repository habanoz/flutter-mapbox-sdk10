package com.habanoz.mapbox.maps10.util

import android.os.Build
import android.view.WindowInsets
import androidx.core.view.WindowInsetsCompat

object CampatUtils {
    fun getStatusBarHeight(insets: WindowInsets): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            println("SDK R "+ insets.getInsets(WindowInsets.Type.statusBars()).top)
            return insets.getInsets(WindowInsets.Type.systemBars()).top
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            println("SDK M "+ insets.systemWindowInsetTop)
            //return insets.systemWindowInsetTop
            return 0
        }

        return 0
    }

    fun getStatusBarHeightCompat(insets: WindowInsetsCompat?): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return insets!!.getInsets(WindowInsetsCompat.Type.statusBars()).top
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            println("SDK M "+ insets!!.systemWindowInsetTop)
            //return insets.systemWindowInsetTop
            return 0
        }

        return 0
    }
}