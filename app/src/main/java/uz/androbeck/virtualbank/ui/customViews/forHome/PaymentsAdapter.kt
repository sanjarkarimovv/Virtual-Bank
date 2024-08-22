package uz.androbeck.virtualbank.ui.customViews.forHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemPaymentsBinding
import uz.androbeck.virtualbank.domain.ui_models.home.PaymentsModel

class PaymentsAdapter : ListAdapter<PaymentsModel, PaymentsAdapter.PaymentsHolder>(diffUtil) {
    inner class PaymentsHolder(private val binding: ItemPaymentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentModel: PaymentsModel) = with(binding) {
            paymentModel.run {
                binding.bgImageLogo.setImageDrawable(
                    ContextCompat.getDrawable(binding.root.context, paymentModel.icon)
                )
                // ...
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PaymentsModel>() {
            override fun areItemsTheSame(oldItem: PaymentsModel, newItem: PaymentsModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PaymentsModel,
                newItem: PaymentsModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsHolder {
        return PaymentsHolder(
            ItemPaymentsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PaymentsHolder, position: Int) {
        holder.bind(getItem(position))
    }
}