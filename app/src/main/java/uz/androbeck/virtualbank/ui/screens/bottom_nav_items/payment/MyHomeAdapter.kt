package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemMyHomeBinding
import uz.androbeck.virtualbank.domain.ui_models.payment_screen.PaymentScreenUIModel
import uz.androbeck.virtualbank.utils.Constants.String.ADD_HOME

class MyHomeAdapter : ListAdapter<PaymentScreenUIModel, MyHomeAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemMyHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentScreenUIModel: PaymentScreenUIModel) {
            with(binding) {
                paymentScreenUIModel.run {
                    title?.let { tvTitle.text = it }
                    logo?.let { ivLogo.setImageResource(it) }
                }
                imAdd.isVisible = paymentScreenUIModel.title == ADD_HOME
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