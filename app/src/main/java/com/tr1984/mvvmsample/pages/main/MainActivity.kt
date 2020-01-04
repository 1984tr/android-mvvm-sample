package com.tr1984.mvvmsample.pages.main

import android.os.Bundle
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.setVariable(BR.viewModel, viewModel)
        setContentView(binding.root)

        subscribeSubjects()

        viewModel.start()
    }
}