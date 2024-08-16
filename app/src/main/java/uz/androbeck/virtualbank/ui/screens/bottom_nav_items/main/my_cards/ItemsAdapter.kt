package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemMyHomeBinding
import uz.androbeck.virtualbank.databinding.MyCardsItemBinding
import uz.androbeck.virtualbank.domain.ui_models.card.GetCardUIModel

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val cardsList = mutableListOf<GetCardUIModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun loadCards(list: List<GetCardUIModel>) {
        cardsList.clear()
        cardsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: MyCardsItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(card: GetCardUIModel)= with(binding){
                userFullName.text=card.owner


            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            MyCardsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return cardsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder
    }


}