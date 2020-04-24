package com.tr1984.mvvmsample.pages.splash

import android.content.Intent
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.extensions.disposeBag
import com.tr1984.mvvmsample.extensions.uiSubscribe
import com.tr1984.mvvmsample.extensions.uiSubscribeWithError
import com.tr1984.mvvmsample.pages.list.ListActivity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashViewModel(override val compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    fun start() {
        FoodsRepository.instance.getFoods()?.uiSubscribe({
            if (it.isEmpty()) {
                insertDefaultData {
                    moveToList()
                }
            } else {
                moveToList()
            }
        }, {
            it.printStackTrace()
        })?.disposeBag(compositeDisposable)
    }

    private fun insertDefaultData(completion: () -> Unit) {
        FoodsRepository.instance.postFoods(Food.dummy)?.doFinally {
            completion.invoke()
        }?.uiSubscribeWithError {
        }?.disposeBag(compositeDisposable)
    }

    private fun moveToList() {
        Single.timer(1, TimeUnit.SECONDS)
            .doFinally {
                navigator.onNext(Navigator.Start(ListActivity::class.java, null, Intent().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }))
                navigator.onNext(Navigator.Finish())
            }.uiSubscribeWithError {

            }.disposeBag(compositeDisposable)
    }
}