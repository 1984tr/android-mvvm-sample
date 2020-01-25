package com.tr1984.mvvmsample.pages.detail

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribe

class DetailViewModel : BaseViewModel() {

    var fileName = ObservableField("")
    var imageUrl = ObservableField("")
    var favorite = ObservableField(false)

    private var foodId: Long? = null

    private var isFavorite = false
        set(value) {
            field = value
            favorite.set(field)
            updateFavorite()
        }

    fun start(foodId: Long) {
        this.foodId = foodId
        FoodsRepository.instance.getFood(foodId)?.uiSubscribe({
            this@DetailViewModel.fileName.set(it.name)
            this@DetailViewModel.imageUrl.set(it.imageUrl)
            this@DetailViewModel.favorite.set(isFavorite)
        }, {
            toastSubject.onNext("Retry later :(")
        })?.disposeBag(compositeDisposable)
    }

    fun toggleFavorite() {
        isFavorite = !isFavorite
    }

    private fun updateFavorite() {
    }
}