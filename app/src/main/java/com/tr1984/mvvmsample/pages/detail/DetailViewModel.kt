package com.tr1984.mvvmsample.pages.detail

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food

class DetailViewModel : BaseViewModel() {

    var fileName = ObservableField("")
    var imageUrl = ObservableField("")
    var favorite = ObservableField(false)

    private var isFavorite = false
        set(value) {
            field = value
            favorite.set(field)
        }

    fun start(foodId: Long) {
        // local
        Food.dummy.let {
            this@DetailViewModel.fileName.set(it.imageUrl)
            this@DetailViewModel.imageUrl.set(it.imageUrl)
            this@DetailViewModel.favorite.set(isFavorite)
        }
    }

    fun toggleFavorite() {
        isFavorite = !isFavorite
    }
}