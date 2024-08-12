package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemPlacesPaymentsBinding
import uz.androbeck.virtualbank.domain.ui_models.payment_screen.PlacesPaymentUIModel

class PlacesPaymentAdapter :
    ListAdapter<PlacesPaymentUIModel, PlacesPaymentAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemPlacesPaymentsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(placesPayment: PlacesPaymentUIModel) {
            with(binding) {
                placesPayment.run {
                    logo?.let { ivLogo.setImageResource(it) }
                    title?.let { tvTitle.text = it }
                    description?.let { tvDescription.text = it }
                    distance?.let { tvDistance.text = "$it m" }
                }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PlacesPaymentUIModel>() {
            override fun areItemsTheSame(oldItem: PlacesPaymentUIModel, newItem: PlacesPaymentUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PlacesPaymentUIModel,
                newItem: PlacesPaymentUIModel,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SavedViewHolder(
        ItemPlacesPaymentsBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}