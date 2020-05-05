package com.example.mydouban.ui.collect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.Collect
import com.example.mydouban.repository.local.dao.CollectDaoOperation

class CollectViewModel(application: Application) : AndroidViewModel(application) {

    val collectList = MutableLiveData<List<Collect>>()

    fun getAllCollect() {
        val all = CollectDaoOperation.getInstance().queryAll(getApplication())
        val collectModelList = ArrayList<Collect>()
        all?.let {
            it.forEach { c ->
                val collect = Collect(
                    c.id,
                    c.title,
                    c.image,
                    c.year,
                    c.average,
                    c.country,
                    c.genres,
                    c.directors,
                    c.casts,
                    c.createTime,
                    c.titleInfo,
                    c.description
                )
                collectModelList.add(collect)
            }
        }
        collectList.postValue(collectModelList)
    }
}
