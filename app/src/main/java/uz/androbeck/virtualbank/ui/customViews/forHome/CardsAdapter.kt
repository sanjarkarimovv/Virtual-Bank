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
                val str = (amount?.toLong() ?: 0).toString()
                tvAmount.text = toSum(str)
                tvCardNumber.text = "$pan **** **** ****"
                tvCardExpired.text =
                    ((expiredYear ?: 1) % 100).toString() + "/" + expiredMonth.toString()
            }
        }

        private fun toSum(str: String): CharSequence {
            var sum = StringBuilder()
            var count = 0
            for (c in str.reversed()) {
                sum.append(c)
                count++
                if (count == 3) {
                    count = 0
                    sum.append(" ")
                }
            }
            sum = sum.reverse()
            return sum.append(" so'm").toString()
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