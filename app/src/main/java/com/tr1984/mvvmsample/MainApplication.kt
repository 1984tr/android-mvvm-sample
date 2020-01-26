package com.tr1984.mvvmsample

import android.app.Application
import com.tr1984.mvvmsample.data.source.FoodsRepository

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        FoodsRepository.instance.initialize(this)
    }
}