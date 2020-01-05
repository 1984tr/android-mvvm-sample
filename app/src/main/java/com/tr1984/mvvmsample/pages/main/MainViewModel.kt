package com.tr1984.mvvmsample.pages.main

import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.pages.list.ListActivity

class MainViewModel : BaseViewModel() {

    fun moveToList() {
        startPageSubject.onNext(StartPageBundle(ListActivity::class.java))
    }

    fun moveToDetail() {
        startPageSubject.onNext(StartPageBundle(DetailActivity::class.java))
    }
}