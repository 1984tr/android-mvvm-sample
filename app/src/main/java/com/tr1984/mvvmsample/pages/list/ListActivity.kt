package com.tr1984.mvvmsample.pages.list

import android.os.Bundle
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.databinding.ActivityListBinding

class ListActivity : BaseActivity<ListViewModel, ActivityListBinding>() {

    var adapter = BaseAdapter(
        hashMapOf(
            ListType01ViewModel::class.java.simpleName to R.layout.item_list_type01,
            ListType02ViewModel::class.java.simpleName to R.layout.item_list_type02
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this
        viewModel.start()
    }

    override var viewModel = ListViewModel()
    override var layoutId = R.layout.activity_list
}