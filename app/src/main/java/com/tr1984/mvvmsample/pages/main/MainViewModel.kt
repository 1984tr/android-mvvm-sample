package com.tr1984.mvvmsample.pages.main

import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.pages.list.ListActivity
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribe

class MainViewModel : BaseViewModel() {

    fun start() {
        FoodsRepository.instance.getFoods()
            .uiSubscribe({

            }, {

            }).disposeBag(compositeDisposable)
    }

    fun moveToList() {
        startPageSubject.onNext(StartPageBundle(ListActivity::class.java))
    }

    fun moveToDetail() {
        startPageSubject.onNext(StartPageBundle(DetailActivity::class.java))
    }
}