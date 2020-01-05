package com.tr1984.mvvmsample.pages.splash

import android.content.Intent
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.pages.main.MainActivity

class SplashViewModel : BaseViewModel() {

    fun start() {
        startPageSubject.onNext(StartPageBundle(MainActivity::class.java, null, Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }))
        finishPageSubject.onNext(FinishPageBundle())
    }
}