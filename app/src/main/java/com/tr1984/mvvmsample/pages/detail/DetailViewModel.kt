package com.tr1984.mvvmsample.pages.detail

import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food

class DetailViewModel : BaseViewModel() {

    var imageUrl: String = ""

    fun start(foodId: Long) {
        // local
        Food.dummy.let {
            this@DetailViewModel.imageUrl = it.imageUrl
        }
    }
}