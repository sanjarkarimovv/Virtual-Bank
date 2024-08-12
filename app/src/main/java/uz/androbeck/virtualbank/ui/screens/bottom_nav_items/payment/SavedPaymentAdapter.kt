package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemSavedPaymentsBinding
import uz.androbeck.virtualbank.domain.ui_models.payment_screen.SavedPaymentUIModel
import uz.androbeck.virtualbank.utils.Constants.String.ADD_HOME

class SavedPaymentAdapter : ListAdapter<SavedPaymentUIModel, SavedPaymentAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemSavedPaymentsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentScreenUIModel: SavedPaymentUIModel) {
            with(binding) {
                paymentScreenUIModel.run {
                    logo?.let { ivLogo.setImageResource(it) }
                    title?.let { tvTitle.text = it }
                    phoneNumber?.let { tvPhoneNumber.text = it }
                }
                mainSave.isVisible = paymentScreenUIModel.title != ADD_HOME
                btnAdd.isVisible = paymentScreenUIModel.title == ADD_HOME
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<SavedPaymentUIModel>() {
            override fun areItemsTheSame(oldItem: SavedPaymentUIModel, newItem: SavedPaymentUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SavedPaymentUIModel, newItem: SavedPaymentUIModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SavedViewHolder(
        ItemSavedPaymentsBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}