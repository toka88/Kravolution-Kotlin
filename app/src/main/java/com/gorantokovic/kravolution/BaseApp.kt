package com.gorantokovic.kravolution

import android.app.Application

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private lateinit var mInstance: BaseApp

        fun getInstance(): BaseApp {
            return mInstance
        }
    }
}