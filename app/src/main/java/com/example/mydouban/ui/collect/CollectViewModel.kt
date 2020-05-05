package com.example.mydouban.ui.collect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.Collect

class CollectViewModel(application: Application) : AndroidViewModel(application) {

    val collectList = MutableLiveData<List<Collect>>()

    fun getAllCollect() {
//        val all = AppDatabase.getInstance(getApplication()).collectDao().getAll()
//        collectList.postValue(all)
    }
}