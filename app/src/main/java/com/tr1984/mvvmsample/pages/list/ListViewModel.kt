package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel

class ListViewModel : BaseViewModel() {

    var adapter = BaseAdapter(
        hashMapOf(
            ListType01ViewModel::class.java.simpleName to R.layout.item_list_type01,
            ListType02ViewModel::class.java.simpleName to R.layout.item_list_type02,
            ListType03ViewModel::class.java.simpleName to R.layout.item_list_type03
        )
    )
    var items = ObservableArrayList<BaseViewModel>()

    fun start() {
        items.add(ListType01ViewModel().apply {
            text.set("ListType01ViewModel")
            actionItemClick = {
                toastSubject.onNext("Click Item")
            }
        })

        items.add(ListType02ViewModel().apply {
            text.set("ListType02ViewModel")
            actionItemClick = {
                toastSubject.onNext("Click Item")
            }
            actionButton01 = {
                toastSubject.onNext("BUTTON 01 !!!!")
            }
            actionButton02 = {
                toastSubject.onNext(it.text.toString())
            }
        })

        items.add(ListType03ViewModel().apply {
            for (i in 0 until 10) {
                items.add(ListType01ViewModel().apply {
                    text.set("SubItem $i")
                    actionItemClick = {
                        toastSubject.onNext("SubItem")
                    }
                })
            }
        })
    }
}