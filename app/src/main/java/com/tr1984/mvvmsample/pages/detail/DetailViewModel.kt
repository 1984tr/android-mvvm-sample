package com.tr1984.mvvmsample.pages.detail

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.extensions.withComma

class DetailViewModel : BaseViewModel() {

    var fileName = ObservableField("")
    var imageUrl = ObservableField("")
    var likeCount = ObservableField("")

    fun start(foodId: Long) {
        // local
        Food.dummy.let {
            this@DetailViewModel.fileName.set(it.imageUrl)
            this@DetailViewModel.imageUrl.set(it.imageUrl)
            this@DetailViewModel.likeCount.set(it.likeCount.withComma())
        }
    }
}