package com.tr1984.mvvmsample.pages.main

import android.os.Bundle
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
    }

    override var viewModel = MainViewModel()
    override var layoutId = R.layout.activity_main
}