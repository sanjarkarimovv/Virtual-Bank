package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.databinding.ItemHistoryInfoBinding
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.formatToHourMinute

class Adapter():PagingDataAdapter<InComeAndOutComeResDto, RecyclerView.ViewHolder>(diffUtil) {

    inner class ContentVH(private val binding: ItemHistoryInfoBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(content: InComeAndOutComeResDto) {
            with(binding) {
                val context = root.context
                tvTransactionDate.text = content.time.formatToHourMinute()
                if (content.type == Constants.History.INCOME) {
                    tvTransactionName.text = content.from
                    tvAmount.text =
                        " + " + content.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_12)
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_income)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_rececived)
                }
                if (content.type == Constants.History.OUTCOME) {
                    tvAmount.text =
                        " - " + content.amount.toString() + " " + context.getString(R.string.str_money_type_uzb)
                    tvTransactionName.text = content.to
                    tvSecondaryTransactionName.text =
                        context.getString(R.string.str_transfer_type_outcome)
                    ivServiceType.setImageResource(R.drawable.ic_transfer_sent)
                    llImageService.setBackgroundResource(R.drawable.bg_shape_corner_radius_15)
                }
            }
        }
    }
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<InComeAndOutComeResDto>() {
            override fun areItemsTheSame(
                oldItem: InComeAndOutComeResDto,
                newItem: InComeAndOutComeResDto
            ): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: InComeAndOutComeResDto,
                newItem: InComeAndOutComeResDto
            ): Boolean {
                return oldItem==newItem
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContentVH(ItemHistoryInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as ContentVH).bind(it) }
    }

}