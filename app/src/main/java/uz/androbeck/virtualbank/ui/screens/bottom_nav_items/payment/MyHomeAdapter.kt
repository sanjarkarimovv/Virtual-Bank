package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemMyHomeBinding
import uz.androbeck.virtualbank.domain.ui_models.payments.PaymentUIModel
import uz.androbeck.virtualbank.utils.Constants.String.ADD_HOME

class MyHomeAdapter : ListAdapter<PaymentUIModel, MyHomeAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemMyHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentUIModel: PaymentUIModel) {
            with(binding) {
                paymentUIModel.run {
                    title?.let { tvTitle.text = it }
                    logo?.let { ivLogo.setImageResource(it) }
                }
                imAdd.isVisible = paymentUIModel.title == ADD_HOME
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PaymentUIModel>() {
            override fun areItemsTheSame(oldItem: PaymentUIModel, newItem: PaymentUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PaymentUIModel, newItem: PaymentUIModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SavedViewHolder(
        ItemMyHomeBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}