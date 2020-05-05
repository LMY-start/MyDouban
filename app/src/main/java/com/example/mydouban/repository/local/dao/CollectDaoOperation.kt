package com.example.mydouban.repository.local.dao

import android.content.Context
import com.example.mydouban.repository.local.db.DbManager
import com.example.mydouban.repository.local.entity.Collect
import com.example.mydouban.repository.local.greendao.CollectDao

class CollectDaoOperation private constructor() {

    private object Holder {
        val instance = CollectDaoOperation()
    }

    companion object {
        fun getInstance(): CollectDaoOperation {
            return Holder.instance
        }
    }

    fun insertData(context: Context?, stu: Collect) {

        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.insert(stu)
    }

    fun insertData(context: Context?, list: List<Collect>?) {
        if (null == list || list.isEmpty()) {
            return
        }
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.insertInTx(list)
    }

    fun saveData(context: Context?, collect: Collect) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.save(collect)
    }

    fun deleteData(context: Context?, collect: Collect) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.delete(collect)
    }

    fun deleteByKeyData(context: Context?, id: Long) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.deleteByKey(id)
    }

    fun deleteAllData(context: Context?) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.deleteAll()
    }

    fun updateData(context: Context?, collect: Collect) {
        DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.update(collect)
    }

    fun queryAll(context: Context?): MutableList<Collect>? {
        val builder =
            DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.queryBuilder()
        return builder?.build()?.list()
    }

    fun queryForTitle(context: Context?, title: String): MutableList<Collect>? {
        val builder =
            DbManager.getInstance(context!!)?.getDaoSession(context)?.collectDao?.queryBuilder()

        return builder?.where(CollectDao.Properties.Title.like(title))?.list()
    }
}