package com.tr1984.mvvmsample.base;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class BaseBindings {

    @BindingAdapter("app:bindItems")
    public static void bindItems(RecyclerView recyclerView, ObservableArrayList<BaseViewModel> items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).bind(items);
        }
    }
}
