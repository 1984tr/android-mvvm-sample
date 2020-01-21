package com.tr1984.mvvmsample.pages.list

import android.content.Intent
import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.Foods
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.viewmodel.MainListFavoriteItemsViewModel
import com.tr1984.mvvmsample.viewmodel.MainListImageItemViewModel
import com.tr1984.mvvmsample.viewmodel.MainListSectionLabelItemViewModel

class ListViewModel : BaseViewModel() {

    var adapter = BaseAdapter(
        hashMapOf(
            MainListSectionLabelItemViewModel::class.java.simpleName to R.layout.main_list_section_label_item,
            MainListFavoriteItemsViewModel::class.java.simpleName to R.layout.main_list_favorite_items,
            MainListImageItemViewModel::class.java.simpleName to R.layout.main_list_image_item
        )
    )
    var items = ObservableArrayList<BaseViewModel>()

    fun start() {
        items.clear()
        items.add(getSection("FAVORITES"))
        items.add(getFavoritesItems())

        items.add(getSection("HOT"))
        Foods.dummy.forEach {
            items.add(getHotItem(it))
        }
    }

    private fun getSection(label: String) : BaseViewModel {
        return MainListSectionLabelItemViewModel().apply {
            this.label.set(label)
        }
    }

    private fun getFavoritesItems() : BaseViewModel {
        return MainListFavoriteItemsViewModel().apply {
            Foods.dummy.forEach {
                items.add(getFavoritesItem(it))
            }
        }
    }

    private fun getFavoritesItem(food: Food) : BaseViewModel {
        return MainListImageItemViewModel().apply {
            this.food = food
            actionItemClick = {
                this@ListViewModel.startPageSubject.onNext(
                    StartPageBundle(
                        clazz = DetailActivity::class.java,
                        intent = Intent().apply {
                            putExtra("food_id", it.id)
                        })
                )
            }
        }
    }

    private fun getHotItem(food: Food) : BaseViewModel {
        return MainListImageItemViewModel().apply {
            this.food = food
            actionItemClick = {
                this@ListViewModel.startPageSubject.onNext(
                    StartPageBundle(
                        clazz = DetailActivity::class.java,
                        intent = Intent().apply {
                            putExtra("food_id", it.id)
                        })
                )
            }
        }
    }
}