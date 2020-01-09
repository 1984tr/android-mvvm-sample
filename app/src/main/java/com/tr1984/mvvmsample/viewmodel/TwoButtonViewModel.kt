package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel

class TwoButtonViewModel : BaseViewModel() {

    var text = ObservableField("")
    var actionItemClick : (() -> Unit)? = null
    var actionButton01: (() -> Unit)? = null
    var actionButton02: ((TwoButtonViewModel) -> Unit)? = null
}