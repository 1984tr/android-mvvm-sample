package com.tr1984.mvvmsample.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr1984.mvvmsample.base.BaseAdapter
import com.tr1984.mvvmsample.base.SubBaseViewModel

@BindingAdapter("loadImage")
fun ImageView.load(url: String?) {
    if (url != null) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("bindList")
fun RecyclerView.bindList(items: ObservableArrayList<SubBaseViewModel>) {
    (adapter as? BaseAdapter)?.run {
        bind(items)
    }
}