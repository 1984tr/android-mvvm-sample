package com.tr1984.mvvmsample.pages.splash

import android.os.Bundle
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SplashViewModel()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.setVariable(BR.viewModel, viewModel)
        setContentView(binding.root)

        subscribeSubjects()

        viewModel.start()
    }
}