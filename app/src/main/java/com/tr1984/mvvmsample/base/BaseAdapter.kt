package com.tr1984.mvvmsample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.R

class BaseAdapter() : RecyclerView.Adapter<BaseAdapter.Holder>() {

    private var items = ObservableArrayList<SubBaseViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.create(parent, viewType)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return items[position].layoutId
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        items.run {
            holder.bind(get(position))

            if (lastItemCount < size && position == size - REQUEST_OFFSET) {
                lastItemCount = size
                requestNext?.invoke()
            }
        }
    }

    fun getItem(position: Int): BaseViewModel? {
        if (position < items.size && position >= 0) {
            return items[position]
        }
        return null
    }

    fun bind(items: ObservableArrayList<BaseViewModel>) {
        if (this.items != items) {
            items.addOnListChangedCallback(listChangedCallback)
            this.items.removeOnListChangedCallback(listChangedCallback)
            this.items = items
            notifyDataSetChanged()
        }
    }

    class Holder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BaseViewModel) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup, layoutId: Int): Holder {
                val inflater = LayoutInflater.from(parent.context)
                return Holder(DataBindingUtil.inflate(inflater, layoutId, parent, false))
            }
        }
    }

    companion object {
        const val REQUEST_OFFSET = 3
    }
}