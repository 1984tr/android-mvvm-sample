package com.tr1984.mvvmsample.pages.detail

import android.os.Bundle
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseActivity
import com.tr1984.mvvmsample.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.start(intent.getLongExtra("food_id", 0))
    }

    override var viewModel = DetailViewModel(compositeDisposable)
    override var layoutId = R.layout.activity_detail
}