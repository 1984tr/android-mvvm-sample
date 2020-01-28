package com.tr1984.mvvmsample.pages.list

import android.content.Intent
import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.data.Food
import com.tr1984.mvvmsample.data.source.FoodsRepository
import com.tr1984.mvvmsample.extensions.disposeBag
import com.tr1984.mvvmsample.extensions.uiSubscribeWithError
import com.tr1984.mvvmsample.pages.detail.DetailActivity
import com.tr1984.mvvmsample.util.RxBus
import com.tr1984.mvvmsample.viewmodel.MainListFavoriteItemsViewModel
import com.tr1984.mvvmsample.viewmodel.MainListImageItemViewModel
import com.tr1984.mvvmsample.viewmodel.MainListSectionLabelItemViewModel
import io.reactivex.Observable

class ListViewModel : BaseViewModel() {

    var adapter = BaseAdapter(
        hashMapOf(
            MainListSectionLabelItemViewModel::class.java.simpleName to R.layout.main_list_section_label_item,
            MainListFavoriteItemsViewModel::class.java.simpleName to R.layout.main_list_favorite_items,
            MainListImageItemViewModel::class.java.simpleName to R.layout.main_list_image_item
        )
    )
    var items = ObservableArrayList<BaseViewModel>()

    init {
        RxBus.listen(RxBus.UpdateFood::class.java)
            .uiSubscribeWithError {
                start()
            }.disposeBag(compositeDisposable)
    }

    fun start() {
        items.clear()
        items.add(getSection("FAVORITES"))
        items.add(getFavoritesItems())

        items.add(getSection("LIST"))
        FoodsRepository.instance.getFoods(false)?.run {
            uiSubscribeWithError {
                items.addAll(getNormalItems(it))
            }.disposeBag(this@ListViewModel.compositeDisposable)
        }
    }

    private fun getSection(label: String): BaseViewModel {
        return MainListSectionLabelItemViewModel().apply {
            this.label.set(label)
        }
    }

    private fun getFavoritesItems(): BaseViewModel {
        return MainListFavoriteItemsViewModel().apply {
            FoodsRepository.instance.getFoods(true)
                ?.flatMapObservable { Observable.fromIterable(it) }?.run {
                uiSubscribeWithError {
                    items.add(getFavoritesItem(it))
                }.disposeBag(this@ListViewModel.compositeDisposable)
            }
        }
    }

    private fun getFavoritesItem(food: Food): BaseViewModel {
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

    private fun getNormalItems(foods: List<Food>): List<BaseViewModel> {
        return foods.map { food ->
            MainListImageItemViewModel().apply {
                this.food = food
                actionItemClick = {
                    this@ListViewModel.startPageSubject.onNext(
                        StartPageBundle(
                            clazz = DetailActivity::class.java,
                            intent = Intent().apply {
                                putExtra("food_id", food.id)
                            })
                    )
                }
            }
        }
    }
}