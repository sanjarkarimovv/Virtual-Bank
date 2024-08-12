package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemServersPaymentBinding
import uz.androbeck.virtualbank.domain.ui_models.payment_screen.PaymentScreenUIModel

class ServicePaymentAdapter : ListAdapter<PaymentScreenUIModel, ServicePaymentAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemServersPaymentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentScreenUIModel: PaymentScreenUIModel) {
            with(binding) {
                paymentScreenUIModel.run {
                    logo?.let { ivLogo.setImageResource(it) }
                    title?.let { tvTitle.text = it }
                }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PaymentScreenUIModel>() {
            override fun areItemsTheSame(oldItem: PaymentScreenUIModel, newItem: PaymentScreenUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PaymentScreenUIModel, newItem: PaymentScreenUIModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SavedViewHolder(
        ItemServersPaymentBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}