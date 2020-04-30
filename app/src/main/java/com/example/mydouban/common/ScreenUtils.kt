package com.example.mydouban.common

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class ScreenUtils private constructor() {
    companion object {
        fun getScreenWidth(context: Context): Int {
            val wm: WindowManager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.getDefaultDisplay().getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        fun getScreenHeight(context: Context): Int {
            val wm: WindowManager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.getDefaultDisplay().getMetrics(outMetrics)
            return outMetrics.heightPixels
        }
    }
}