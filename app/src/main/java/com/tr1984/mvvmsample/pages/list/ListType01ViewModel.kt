package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel

class ListType01ViewModel : BaseViewModel() {

    var text = ObservableField("")
    var actionItemClick : (() -> Unit)? = null
}