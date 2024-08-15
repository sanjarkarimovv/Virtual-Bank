package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemMyCardsBinding

class MyCardsAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<MyCardsAdapter.MyCardsViewHolder>() {


    inner class MyCardsViewHolder(val binding: ItemMyCardsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvMyCards.text = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardsViewHolder {
        return MyCardsViewHolder(
            ItemMyCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyCardsViewHolder, position: Int) {
        holder.bind(list[position])
    }
}