package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel

class ListType02ViewModel : BaseViewModel() {

    var text = ObservableField("")
    var actionButton01: (() -> Unit)? = null
    var actionButton02: ((ListType02ViewModel) -> Unit)? = null
}