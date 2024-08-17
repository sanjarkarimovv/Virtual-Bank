package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ItemHistoryHeaderBinding
import uz.androbeck.virtualbank.databinding.ItemHistoryInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.ui.enums.HistoryItemViewType
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.formatToDayMonthYear
import uz.androbeck.virtualbank.utils.extentions.formatToHourMinute
import uz.androbeck.virtualbank.utils.extentions.singleClickable


class HistoryAdapter(
    private val onClick: (HistoryItem.Content) -> Unit
) : PagingDataAdapter<HistoryItem, RecyclerView.ViewHolder>(diffUtil) {


    inner class HeaderVH(private val binding: ItemHistoryHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(header: HistoryItem.Header) {
            binding.tvHeader.text = header.time?.formatToDayMonthYear()
        }
    }

    inner class ContentVH(private val binding: ItemHistoryInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SuspiciousIndentation")
        fun bind(content: HistoryItem.Content) {

            with(binding) {
                val context = root.context
                tvTransactionDate.text = content.child.time.formatToHourMinute()
                if (content.child.type == Constants.History.INCOME) {
                    tvTransactionName.text = content.child.from
                    tvAmount.text =
                        " + " + content.child.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_12)
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_income)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_rececived)
                }
                if (content.child.type == Constants.History.OUTCOME) {
                    tvAmount.text =
                        " - " + content.child.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    tvTransactionName.text = content.child.to
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_outcome)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_sent)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_15)
                }
                root.singleClickable {
                    onClick(content)
                }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<HistoryItem>() {
                override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
                    return oldItem == newItem
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HistoryItemViewType.Header.ordinal -> HeaderVH(
                ItemHistoryHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            HistoryItemViewType.Content.ordinal -> ContentVH(
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
            null -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HistoryItem.Header -> HistoryItemViewType.Header.ordinal
            is HistoryItem.Content -> HistoryItemViewType.Content.ordinal
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

            binding.tvHeader.text = currentItem.time?.formatToDayMonthYear()
            return binding.root
        }
        return null
    }


}
