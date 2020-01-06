package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.base.BaseViewModel

class ListViewModel : BaseViewModel() {

    var items = ObservableArrayList<BaseViewModel>()

    fun start() {
        items.add(ListType01ViewModel().apply {
            text.set("ListType01ViewModel")
        })

        items.add(ListType02ViewModel().apply {
            text.set("ListType02ViewModel")
            actionButton01 = {
                toastSubject.onNext("BUTTON 01 !!!!")
            }
            actionButton02 = {
                toastSubject.onNext(it.text.toString())
            }
        })
    }
}