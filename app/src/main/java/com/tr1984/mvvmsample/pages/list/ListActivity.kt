package com.tr1984.mvvmsample.pages.list

import android.os.Bundle
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivityListBinding

class ListActivity : BaseActivity<ListViewModel, ActivityListBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override var viewModel = ListViewModel()
    override var layoutId = R.layout.activity_list
}