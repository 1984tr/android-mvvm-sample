package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel

class MainListSectionLabelItemViewModel(override val layoutId: Int, override val parent: BaseViewModel) :
    SubBaseViewModel(layoutId, parent) {

    var label = ObservableField("")

    override fun identification(): String {
        return "MainListSectionLabelItemViewModel:${hashCode()}"
    }
}