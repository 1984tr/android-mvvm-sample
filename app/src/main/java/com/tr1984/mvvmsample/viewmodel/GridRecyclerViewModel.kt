package com.tr1984.mvvmsample.viewmodel

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel

class GridRecyclerViewModel: BaseViewModel()  {

    var adapter = BaseAdapter(
        hashMapOf(
            PlainTextViewModel::class.java.simpleName to R.layout.item_grid_recyclerview_cell
        )
    )
    var items = ObservableArrayList<BaseViewModel>()
}