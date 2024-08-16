package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ItemMyHomeBinding
import uz.androbeck.virtualbank.databinding.MyCardsItemBinding
import uz.androbeck.virtualbank.domain.mock_data.AppHardcodeData
import uz.androbeck.virtualbank.ui.enums.CardType

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

            @SuppressLint("SetTextI18n")
            fun bind(card: GetCardUIModel){
                val exYear= card.expiredYear?.dropLast(2)
                binding.userFullName.text=card.owner
                binding.cardNumber.text=card.pan
                binding.cardBalance.text=card.amount
                binding.expireData.text="${card.expiredMonth}/$exYear"
                binding.importantText.text=card.cardImportant
                binding.cardLogo.setImageResource(determineCardType(card.pan))
                binding.cardName.text=card.cardName
                binding.cardPhoto.setImageResource(AppHardcodeData.cardStyleImages[card.themeType?.toInt()?:0])


            }
        }
    fun determineCardType(cardNumber:String?):Int{
        val card= cardNumber?.drop(4)
        return when(card){
            "8600","5614"-> R.drawable.img_uzcard_logo
            "9860"->R.drawable.img_humo_logo
            else ->R.drawable.ic_cards
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
        holder.bind(cardsList[position])
    }


}