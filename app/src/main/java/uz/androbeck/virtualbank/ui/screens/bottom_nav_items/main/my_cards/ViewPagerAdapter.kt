package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.MyCardsItemPagerBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyCardsViewHolder>() {

    private val cardsList= mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun load(list:List<String>){
        cardsList.clear()
        cardsList.addAll(list)
        notifyDataSetChanged()
    }


    inner class MyCardsViewHolder(val binding: MyCardsItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvMyCards.text = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardsViewHolder {
        return MyCardsViewHolder(
            MyCardsItemPagerBinding.inflate(
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