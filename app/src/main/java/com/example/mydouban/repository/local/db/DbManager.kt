package com.example.mydouban.repository.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.mydouban.repository.local.greendao.DaoMaster
import com.example.mydouban.repository.local.greendao.DaoSession

class DbManager private constructor(mContext: Context) {

    private val DB_NAME = "douban.db"
    private var mDevOpenHelper: DaoMaster.DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    init {
        mDevOpenHelper = DaoMaster.DevOpenHelper(mContext, DB_NAME)
        getDaoMaster(mContext)
        getDaoSession(mContext)
    }

    companion object {
        @Volatile
        var instance: DbManager? = null

        fun getInstance(mContext: Context): DbManager? {
            if (instance == null) {
                synchronized(DbManager::class) {
                    if (instance == null) {
                        instance = DbManager(mContext)
                    }
                }
            }
            return instance
        }
    }

    fun getReadableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }
        return mDevOpenHelper?.getReadableDatabase()
    }


    fun getWritableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }

        return mDevOpenHelper?.getWritableDatabase()
    }

    fun getDaoMaster(context: Context): DaoMaster? {
        if (null == mDaoMaster) {
            synchronized(DbManager::class.java) {
                if (null == mDaoMaster) {
                    mDaoMaster = DaoMaster(getWritableDatabase(context))
                }
            }
        }
        return mDaoMaster
    }

    fun getDaoSession(context: Context): DaoSession? {
        if (null == mDaoSession) {
            synchronized(DbManager::class.java) {
                mDaoSession = getDaoMaster(context)?.newSession()
            }
        }

        return mDaoSession
    }
}