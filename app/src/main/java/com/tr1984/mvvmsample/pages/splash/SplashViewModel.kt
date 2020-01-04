package com.tr1984.mvvmsample.pages.splash

import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.pages.main.MainActivity

class SplashViewModel : BaseViewModel() {

    fun start() {
        putPageSubject.onNext(Triple(MainActivity::class.java, 0, null))
        popPageSubject.onNext(Pair(null, null))
    }
}