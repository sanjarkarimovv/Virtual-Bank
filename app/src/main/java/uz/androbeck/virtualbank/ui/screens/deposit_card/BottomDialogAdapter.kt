package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel

class BottomDialogAdapter(
    private val cards: List<CardUIModel>,
) : RecyclerView.Adapter<BottomDialogAdapter.ViewHolder>() {
    inner class ViewHolder(
        val binding: ItemCardsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getCardsUiModel: CardUIModel) {
            binding.tvCardName.text = getCardsUiModel.name
            binding.tvCardBalance.text = getCardsUiModel.amount.toString()
            binding.tvCardNumber.text = getCardsUiModel.pan.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position])
    }
}