package uz.androbeck.virtualbank.ui.customViews.forHome

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemLastTransferBinding
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import java.text.SimpleDateFormat

class LastTransferAdapter :
    ListAdapter<InComeAndOutComeUIModel, LastTransferAdapter.LastTransferHolder>(diffUtil) {
    inner class LastTransferHolder(private val binding: ItemLastTransferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(lastTransferModel: InComeAndOutComeUIModel) = with(binding) {
            lastTransferModel.run {
                tvAmount.text = "+$amount"
                etDate.text = SimpleDateFormat("dd MMMM").format(time)
                // ...
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<InComeAndOutComeUIModel>() {
            override fun areItemsTheSame(
                oldItem: InComeAndOutComeUIModel,
                newItem: InComeAndOutComeUIModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: InComeAndOutComeUIModel,
                newItem: InComeAndOutComeUIModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastTransferHolder {
        return LastTransferHolder(
            ItemLastTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LastTransferHolder, position: Int) {
        holder.bind(getItem(position))
    }
}