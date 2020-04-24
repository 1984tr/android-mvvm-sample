package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel
import com.tr1984.mvvmsample.data.Food

class MainListImageItemViewModel(override val layoutId: Int, override val parent: BaseViewModel) :
    SubBaseViewModel(layoutId, parent) {

    var food: Food? = null
        set(value) {
            field = value
            imageUrl.set(value?.imageUrl)
        }
    var imageUrl = ObservableField("")
    var actionItemClick: ((Food) -> Unit)? = null
    var actionFavoriteClick: ((Food) -> Unit)? = null

    override fun identification(): String {
        return "MainListImageItemViewModel${hashCode()}"
    }
}