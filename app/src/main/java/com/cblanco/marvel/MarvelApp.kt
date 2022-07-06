package com.cblanco.marvel

import android.app.Application

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}