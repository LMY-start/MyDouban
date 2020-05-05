package com.example.mydouban.common

import android.os.Looper
import androidx.lifecycle.MutableLiveData

/**
 * Utilities for update [MutableLiveData]
 *
 * @author https://github.com/funnywolfdadada
 * @since 2020/2/16
 */
private val mainHandler = android.os.Handler(Looper.getMainLooper())

fun <T> MutableLiveData<T>.update(d: T?) {
    if (Thread.currentThread() == Looper.getMainLooper().thread) {
        value = d
    } else {
        postValue(d)
    }
}

