package com.tr1984.mvvmsample.pages.detail

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.extensions.disposeBag
import com.tr1984.mvvmsample.extensions.uiSubscribe
import com.tr1984.mvvmsample.util.RxBus
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(override val compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    var fileName = ObservableField("")
    var imageUrl = ObservableField("")
    var favorite = ObservableField(false)

    private var foodId: Long? = null

    private var isFavorite = false
        set(value) {
            field = value
            favorite.set(field)
        }

    fun start(foodId: Long) {
        this.foodId = foodId
        FoodsRepository.instance.getFood(foodId)?.uiSubscribe({
            this@DetailViewModel.fileName.set(it.name)
            this@DetailViewModel.imageUrl.set(it.imageUrl)
            this@DetailViewModel.isFavorite = it.isFavorite
        }, {
            notifier.onNext(Notifier.Toast("Retry later :("))
        })?.disposeBag(compositeDisposable)
    }

    fun toggleFavorite() {
        foodId?.run {
            FoodsRepository.instance.putFood(this, !isFavorite)?.uiSubscribe({
                isFavorite = !isFavorite
                RxBus.publish(RxBus.UpdateFood(this, isFavorite))
            }, {
                notifier.onNext(Notifier.Toast("Retry later :("))
            })?.disposeBag(compositeDisposable)
        } ?: notifier.onNext(Notifier.Toast("Retry later :("))
    }
}