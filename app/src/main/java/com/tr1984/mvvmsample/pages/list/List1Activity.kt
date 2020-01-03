package com.tr1984.mvvmsample.pages.list

import android.os.Bundle
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivityList1Binding

class List1Activity : BaseActivity<List1ViewModel, ActivityList1Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = List1ViewModel()
        binding = ActivityList1Binding.inflate(layoutInflater)
        binding.setVariable(BR.viewModel, viewModel)
        setContentView(binding.root)
    }
}