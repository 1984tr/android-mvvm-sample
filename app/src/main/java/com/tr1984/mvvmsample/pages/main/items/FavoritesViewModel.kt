package com.tr1984.mvvmsample.pages.main.items

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel

class FavoritesViewModel(override val layoutId: Int, override val parent: BaseViewModel) :
    SubBaseViewModel(layoutId, parent) {

    var adapter = BaseAdapter()
    var items = ObservableArrayList<SubBaseViewModel>()

    override fun identification(): String {
        return "FavoritesViewModel:${hashCode()}"
    }
}