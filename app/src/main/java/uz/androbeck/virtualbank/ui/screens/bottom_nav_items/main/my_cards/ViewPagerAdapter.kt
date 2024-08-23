package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.MyCardsItemViewPagerBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyCardsViewHolder>() {

    private val cardsList= mutableListOf<List<CardUIModel>>()

    @SuppressLint("NotifyDataSetChanged")
    fun load(list:List<List<CardUIModel>>){
        cardsList.clear()
        cardsList.addAll(list)
        notifyDataSetChanged()
    }


    inner class MyCardsViewHolder(val binding: MyCardsItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: List<CardUIModel>) {
            val  adapter = ItemsAdapter()
            adapter.loadCards(item)
            binding.recyclerView.adapter = adapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardsViewHolder {
        return MyCardsViewHolder(
            MyCardsItemViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: MyCardsViewHolder, position: Int) {
        holder.bind(cardsList[position])
    }
}