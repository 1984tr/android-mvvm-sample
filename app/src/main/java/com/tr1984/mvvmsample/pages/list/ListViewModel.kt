package com.tr1984.mvvmsample.pages.list

import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Foods
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.viewmodel.GridRecyclerViewModel
import com.tr1984.mvvmsample.viewmodel.PlainTextViewModel
import com.tr1984.mvvmsample.viewmodel.SimpleImageViewModel
import com.tr1984.mvvmsample.viewmodel.TwoButtonViewModel

class ListViewModel : BaseViewModel() {

    var adapter = BaseAdapter(
        hashMapOf(
            PlainTextViewModel::class.java.simpleName to R.layout.item_plain_text,
            TwoButtonViewModel::class.java.simpleName to R.layout.item_two_button,
            GridRecyclerViewModel::class.java.simpleName to R.layout.item_grid_recyclerview,
            SimpleImageViewModel::class.java.simpleName to R.layout.item_simple_image
        )
    )
    var items = ObservableArrayList<BaseViewModel>()

    fun start() {
        items.clear()
        items.add(PlainTextViewModel().apply {
            text.set("ROW1")
            actionItemClick = {
                this@ListViewModel.toastSubject.onNext("Click Item")
            }
        })

        items.add(TwoButtonViewModel().apply {
            text.set("ROW2")
            actionItemClick = {
                this@ListViewModel.toastSubject.onNext("Click Item")
            }
            actionButton01 = {
                this@ListViewModel.toastSubject.onNext("BUTTON 01 !!!!")
            }
            actionButton02 = {
                this@ListViewModel.toastSubject.onNext(text.toString())
            }
        })

//        items.add(GridRecyclerViewModel().apply {
//            for (i in 0 until 10) {
//                items.add(PlainTextViewModel().apply {
//                    text.set("SubItem $i")
//                    actionItemClick = {
//                        Log.d("trtr", "SubItem -> actionItemClick")
//                        this@ListViewModel.toastSubject.onNext("SubItem")
//                    }
//                })
//            }
//        })

        Foods.dummy.forEach {
            items.add(SimpleImageViewModel().apply {
                food = it
                actionItemClick = {
                    this@ListViewModel.startPageSubject.onNext(StartPageBundle(DetailActivity::class.java))
                }
            })
        }
    }
}