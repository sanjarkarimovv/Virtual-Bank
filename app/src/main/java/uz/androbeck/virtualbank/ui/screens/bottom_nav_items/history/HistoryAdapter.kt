package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.common.response.transfer.Child
import uz.androbeck.virtualbank.databinding.ItemHistoryHeaderBinding
import uz.androbeck.virtualbank.databinding.ItemHistoryInfoBinding
import java.text.SimpleDateFormat
import java.util.Locale

sealed class HistoryItem {
    data class Header(val time: Long?) : HistoryItem()
    data class Content(val child: Child) : HistoryItem()
}

class HistoryAdapter(
    private val onClick: (HistoryItem.Content) -> Unit
) : ListAdapter<HistoryItem, RecyclerView.ViewHolder>(diffUtil) {

    inner class HeaderVH(private val binding: ItemHistoryHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: HistoryItem.Header) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            binding.tvHeader.text = dateFormat.format(header.time)
        }
    }

    inner class ContentVH(private val binding: ItemHistoryInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: HistoryItem.Content) {

            with(binding) {

                tvAmount.text = content.child.amount.toString() + " so'm"
                val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                tvTransactionDate.text = dateFormat.format(content.child.time)
                if (content.child.type == "income") {
                    tvTransactionName.text = content.child.from
                    tvSecondaryTransactionName.text = "Qabulqilindi"
                    ivServiceType.setImageResource(R.drawable.ic_transfer_rececived)
                }
                if (content.child.type == "outcome") {
                    tvTransactionName.text = content.child.to
                    tvSecondaryTransactionName.text = "Tolov"
                    ivServiceType.setImageResource(R.drawable.ic_transfer_sent)
                }
            }

            binding.root.setOnClickListener {
                onClick(content)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<HistoryItem>() {
            override fun areItemsTheSame(
                oldItem: HistoryItem,
                newItem: HistoryItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HistoryItem,
                newItem: HistoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.Header.ordinal -> HeaderVH(
                ItemHistoryHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewType.Content.ordinal -> ContentVH(
                ItemHistoryInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is HistoryItem.Header -> (holder as HeaderVH).bind(item)
            is HistoryItem.Content -> (holder as ContentVH).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HistoryItem.Header -> ViewType.Header.ordinal
            is HistoryItem.Content -> ViewType.Content.ordinal
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    fun findHeaderViewForPosition1(position: Int, parent: RecyclerView): View? {
        val currentItem = getItem(position)
        if (currentItem is HistoryItem.Header) {
            val binding = ItemHistoryHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            binding.tvHeader.text = dateFormat.format(currentItem.time)
            return binding.root
        }
        return null
    }


    enum class ViewType {
        Header,
        Content
    }
}
