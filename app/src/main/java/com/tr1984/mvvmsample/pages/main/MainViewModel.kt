package com.tr1984.mvvmsample.pages.main

import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.pages.list.ListActivity
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribe
import com.tr1984.mvvmsample.util.uiSubscribeWithError

class MainViewModel : BaseViewModel() {

    fun start() {
        FoodsRepository.instance.getFoods()?.uiSubscribe({
            if (it.isEmpty()) {
                insertDefaultData()
            }
        }, {
            it.printStackTrace()
        })?.disposeBag(compositeDisposable)
    }

    fun moveToList() {
        startPageSubject.onNext(StartPageBundle(ListActivity::class.java))
    }

    fun moveToDetail() {
        startPageSubject.onNext(StartPageBundle(DetailActivity::class.java))
    }

    private fun insertDefaultData() {
        FoodsRepository.instance.postFoods(Food.dummy)?.uiSubscribeWithError {
        }?.disposeBag(compositeDisposable)
    }
}