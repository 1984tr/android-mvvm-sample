package com.tr1984.mvvmsample.pages.main.items

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel
import com.tr1984.mvvmsample.data.Food

class ImageItemViewModel(override val layoutId: Int, override val parent: BaseViewModel) :
    SubBaseViewModel(layoutId, parent) {

    var food: Food? = null
        set(value) {
            field = value
            imageUrl.set(value?.imageUrl)
            isSelected.set(value?.isFavorite ?: false)
        }
    var imageUrl = ObservableField("")
    var isSelected = ObservableBoolean(false)
    var actionItemClick: ((Food) -> Unit)? = null
    var actionFavoriteClick: ((Food) -> Unit)? = null

    override fun identification(): String {
        return "ImageItemViewModel:${hashCode()}"
    }
}