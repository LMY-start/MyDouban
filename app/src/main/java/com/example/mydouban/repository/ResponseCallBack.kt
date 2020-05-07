package com.example.mydouban.repository

import java.lang.Exception

interface ResponseCallBack<T> {
    fun onSuccess(t: T)

    fun onError(e: Exception?)
}