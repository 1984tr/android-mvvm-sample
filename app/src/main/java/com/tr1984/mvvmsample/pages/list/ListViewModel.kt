package com.tr1984.mvvmsample.pages.list

import android.content.Intent
import androidx.databinding.ObservableArrayList
import com.tr1984.mvvmsample.R
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.BaseViewModel
import com.tr1984.mvvmsample.base.SubBaseViewModel
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
import io.reactivex.disposables.CompositeDisposable

class ListViewModel(override val compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    var adapter = BaseAdapter()
    var items = ObservableArrayList<SubBaseViewModel>()

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

    private fun getSection(label: String): SubBaseViewModel {
        return MainListSectionLabelItemViewModel(R.layout.main_list_section_label_item, this).apply {
            this.label.set(label)
        }
    }

    private fun getFavoritesItems(): SubBaseViewModel {
        return MainListFavoriteItemsViewModel(R.layout.main_list_favorite_items, this).apply {
            FoodsRepository.instance.getFoods(true)
                ?.flatMapObservable { Observable.fromIterable(it) }?.run {
                uiSubscribeWithError {
                    items.add(getFavoritesItem(it))
                }.disposeBag(this@ListViewModel.compositeDisposable)
            }
        }
    }

    private fun getFavoritesItem(food: Food): SubBaseViewModel {
        return MainListImageItemViewModel(R.layout.main_list_image_item, this).apply {
            this.food = food
            actionItemClick = {
                this@ListViewModel.navigator.onNext(
                    Navigator.Start(
                        clazz = DetailActivity::class.java,
                        intent = Intent().apply {
                            putExtra("food_id", it.id)
                        })
                )
            }
        }
    }

    private fun getNormalItems(foods: List<Food>): List<SubBaseViewModel> {
        return foods.map { food ->
            MainListImageItemViewModel(R.layout.main_list_image_item, this).apply {
                this.food = food
                actionItemClick = {
                    this@ListViewModel.navigator.onNext(
                        Navigator.Start(
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