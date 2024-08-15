package uz.androbeck.virtualbank.ui.dialogs.show_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemBottomDialogCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.home.CardModel

class ShowDialogAdapter : ListAdapter<CardModel, ShowDialogAdapter.CardsHolder>(diffUtil) {
    inner class CardsHolder(private val binding: ItemBottomDialogCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardModel: CardModel) = with(binding) {
            cardModel.run {

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
            ItemBottomDialogCardsBinding.inflate(
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