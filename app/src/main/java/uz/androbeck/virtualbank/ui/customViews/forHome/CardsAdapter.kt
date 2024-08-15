package uz.androbeck.virtualbank.ui.customViews.forHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.home.CardModel

class CardsAdapter : ListAdapter<CardModel, CardsAdapter.CardsHolder>(diffUtil) {
    inner class CardsHolder(private val binding: ItemCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardModel: CardModel) = with(binding) {
            cardModel.run {
                tvOwnerName.text = name
                tvAmount.text = amount
                // ...
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CardModel>() {
            override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsHolder {
        return CardsHolder(
            ItemCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CardsHolder, position: Int) {
        holder.bind(getItem(position))
    }
}