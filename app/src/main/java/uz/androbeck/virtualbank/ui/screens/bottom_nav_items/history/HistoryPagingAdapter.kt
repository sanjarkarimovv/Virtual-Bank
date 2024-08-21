package uz.androbeck.virtualbank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ItemHistoryInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.formatToHourMinute

class HistoryPagingAdapter :
    PagingDataAdapter<InComeAndOutComeUIModel, HistoryPagingAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class HistoryViewHolder(private val binding: ItemHistoryInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InComeAndOutComeUIModel) {

            binding.apply {
                val context = root.context
                tvTransactionDate.text = item.time.formatToHourMinute()
                if (item.type == Constants.History.INCOME) {
                    tvTransactionName.text = item.from
                    tvAmount.text =
                        " + " + item.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_12)
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_income)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_rececived)
                }
                if (item.type == Constants.History.OUTCOME) {
                    tvAmount.text =
                        " - " + item.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    tvTransactionName.text = item.to
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_outcome)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_sent)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_15)
                }
            }
        }
    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<InComeAndOutComeUIModel>() {
        override fun areItemsTheSame(
            oldItem: InComeAndOutComeUIModel,
            newItem: InComeAndOutComeUIModel
        ): Boolean = oldItem.time == newItem.time

        override fun areContentsTheSame(
            oldItem: InComeAndOutComeUIModel,
            newItem: InComeAndOutComeUIModel
        ): Boolean = oldItem == newItem
    }
}
