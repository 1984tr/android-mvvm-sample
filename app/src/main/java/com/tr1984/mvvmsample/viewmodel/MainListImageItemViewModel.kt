package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food

class MainListImageItemViewModel : BaseViewModel() {

    var food: Food? = null
        set(value) {
            field = value
            imageUrl.set(value?.imageUrl)
        }
    var imageUrl = ObservableField("")
    var actionItemClick: ((Food) -> Unit)? = null
    var actionFavoriteClick: ((Food) -> Unit)? = null
}