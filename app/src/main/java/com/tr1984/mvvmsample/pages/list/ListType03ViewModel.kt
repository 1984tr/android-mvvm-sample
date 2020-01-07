package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel

class ListType03ViewModel: BaseViewModel()  {

    var adapter = BaseAdapter(
        hashMapOf(
            ListType01ViewModel::class.java.simpleName to R.layout.item_list_type01,
            ListType02ViewModel::class.java.simpleName to R.layout.item_list_type02
        )
    )
    var items = ObservableArrayList<BaseViewModel>()
}