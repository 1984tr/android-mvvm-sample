package com.tr1984.mvvmsample.pages.splash

import android.content.Intent
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.pages.list.ListActivity
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribe
import com.tr1984.mvvmsample.util.uiSubscribeWithError
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class SplashViewModel : BaseViewModel() {

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
                startPageSubject.onNext(StartPageBundle(ListActivity::class.java, null, Intent().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }))
                finishPageSubject.onNext(FinishPageBundle())
            }.uiSubscribeWithError {

            }.disposeBag(compositeDisposable)
    }
}