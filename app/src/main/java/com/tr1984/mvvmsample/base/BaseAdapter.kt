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

class BaseAdapter(
    private val layoutMap: HashMap<String, Int>,
    private val requestNext: (() -> Unit)? = null
) :
    RecyclerView.Adapter<BaseAdapter.Holder>() {

    private var items = ObservableArrayList<BaseViewModel>()

    private var listChangedCallback = object :
        ObservableList.OnListChangedCallback<ObservableList<BaseViewModel>>() {

        override fun onChanged(sender: ObservableList<BaseViewModel>) {
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(
            sender: ObservableList<BaseViewModel>,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(
            sender: ObservableList<BaseViewModel>,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(
            sender: ObservableList<BaseViewModel>,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            notifyItemRangeRemoved(fromPosition, itemCount)
            notifyItemRangeInserted(toPosition, itemCount)
        }

        override fun onItemRangeRemoved(
            sender: ObservableList<BaseViewModel>,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRangeRemoved(positionStart, itemCount)
            if (lastItemCount > items.size) {
                lastItemCount = items.size
            }
            if (items.size <= 0) {
                lastItemCount = 0
            }
        }
    }

    private var lastItemCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.create(parent, viewType)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return layoutMap[items[position]::class.java.simpleName] ?: R.layout.view_empty
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