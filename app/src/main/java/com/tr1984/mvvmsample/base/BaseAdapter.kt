package com.tr1984.mvvmsample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mvvmsample.BR

class BaseAdapter : RecyclerView.Adapter<BaseAdapter.Holder>() {

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
        }
    }

    fun getItem(position: Int): SubBaseViewModel? {
        if (position < items.size && position >= 0) {
            return items[position]
        }
        return null
    }

    fun bind(newItems: ObservableArrayList<SubBaseViewModel>) {
        val callback = DiffCallback(items, newItems)
        val result = DiffUtil.calculateDiff(callback)

        items.clear()
        items.addAll(newItems)

        result.dispatchUpdatesTo(this)
    }

    class Holder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: SubBaseViewModel) {
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

    inner class DiffCallback(
        private val oldList: List<SubBaseViewModel>,
        private val newList: List<SubBaseViewModel>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].identification() == newList[newItemPosition].identification()
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    companion object {
        const val THRESHOLD = 3
    }
}