package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel

class PlainTextViewModel : BaseViewModel() {

    var text = ObservableField("")
    var actionItemClick : (() -> Unit)? = null
}