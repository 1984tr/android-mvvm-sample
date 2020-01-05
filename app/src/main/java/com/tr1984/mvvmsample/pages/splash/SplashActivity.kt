package com.tr1984.mvvmsample.pages.splash

import android.os.Bundle
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
    }

    override var viewModel = SplashViewModel()
    override var layoutId = R.layout.activity_splash

}