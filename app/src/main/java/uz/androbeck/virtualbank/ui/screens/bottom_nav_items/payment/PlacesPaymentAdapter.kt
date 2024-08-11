package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.test.PlacesPayment
import uz.androbeck.virtualbank.databinding.ItemPlacesPaymentsBinding

class PlacesPaymentAdapter :
    ListAdapter<PlacesPayment, PlacesPaymentAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemPlacesPaymentsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(placesPayment: PlacesPayment) {
            with(binding) {
                placesPayment.logo?.let { ivLogo.setImageResource(it) }
                placesPayment.title?.let { tvTitle.text = it }
                placesPayment.description?.let { tvDescription.text = it }
                placesPayment.distance?.let { tvDistance.text = "$it m" }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PlacesPayment>() {
            override fun areItemsTheSame(oldItem: PlacesPayment, newItem: PlacesPayment): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PlacesPayment,
                newItem: PlacesPayment,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder(
            ItemPlacesPaymentsBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}