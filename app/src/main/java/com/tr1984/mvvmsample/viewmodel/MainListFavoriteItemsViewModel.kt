package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel

class MainListFavoriteItemsViewModel: BaseViewModel()  {

    var adapter = BaseAdapter(
        hashMapOf(
            MainListImageItemViewModel::class.java.simpleName to R.layout.main_list_favorite_image_item
        )
    )
    var items = ObservableArrayList<BaseViewModel>()
}