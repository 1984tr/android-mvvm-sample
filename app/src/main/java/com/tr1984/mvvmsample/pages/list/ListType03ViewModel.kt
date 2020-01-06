package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.base.BaseViewModel

class ListType03ViewModel: BaseViewModel()  {

    var items = ObservableArrayList<BaseViewModel>()
}