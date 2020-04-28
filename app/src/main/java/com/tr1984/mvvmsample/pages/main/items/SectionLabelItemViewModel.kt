package com.tr1984.mvvmsample.pages.main.items

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel

class SectionLabelItemViewModel(override val layoutId: Int, override val parent: BaseViewModel) :
    SubBaseViewModel(layoutId, parent) {

    var label = ObservableField("")

    override fun identification(): String {
        return "SectionLabelItemViewModel:${hashCode()}"
    }
}