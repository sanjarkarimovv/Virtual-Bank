package uz.androbeck.virtualbank.ui.customViews.forHome

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel


class CardsAdapter : ListAdapter<CardUIModel, CardsAdapter.CardsHolder>(diffUtil) {
    inner class CardsHolder(private val binding: ItemCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cardModel: CardUIModel) = with(binding) {
            cardModel.run {
                tvOwnerName.text = name
                tvAmount.text = amount.toString()
                tvCardNumber.text = pan.toString()
                tvCardExpired.text = expiredYear.toString() + "/" + expiredMonth.toString()
                //tvAmount.text = amount
                // ...
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CardUIModel>() {
            override fun areItemsTheSame(oldItem: CardUIModel, newItem: CardUIModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CardUIModel, newItem: CardUIModel): Boolean {
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