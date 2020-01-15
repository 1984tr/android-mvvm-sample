package com.tr1984.mvvmsample.base;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class BaseBindings {

    @BindingAdapter("app:bindItems")
    public static void bindItems(RecyclerView recyclerView, ObservableArrayList<BaseViewModel> items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).bind(items);
        }
    }

    @BindingAdapter("app:loadSimpleImage")
    public static void loadSimpleImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView).load(imageUrl).into(imageView);
    }
}
