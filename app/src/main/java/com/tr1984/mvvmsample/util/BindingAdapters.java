package com.tr1984.mvvmsample.util;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.tr1984.mvvmsample.base.BaseAdapter;
import com.tr1984.mvvmsample.base.BaseViewModel;

public class BindingAdapters {

    @BindingAdapter("bindItems")
    public static void bindItems(RecyclerView recyclerView, ObservableArrayList<BaseViewModel> items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).bind(items);
        }
    }
}
