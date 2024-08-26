package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.MyCardsItemRecycleViewBinding
import uz.androbeck.virtualbank.domain.mock_data.AppHardcodeData
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.singleClickable

class ItemsAdapter(
    private  val listener:(CardUIModel)->Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    val cardsList = mutableListOf<CardUIModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun loadCards(list: List<CardUIModel>) {
        cardsList.clear()
        cardsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: MyCardsItemRecycleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(card: CardUIModel) = with(binding) {
            root.singleClickable{
                listener(card)
            }

            val exYear = (card.expiredYear)?.rem(1000)
            var formattedText = Constants.String.FULL_CARD_NUMBER_STARS + card.pan
            if (card.pan?.length!! > 4) {
                formattedText = card.pan?.substring(0, 4) + " " +
                        card.pan?.substring(4, 6) + Constants.String.HALF_CARD_NUMBER +
                        card.pan?.substring(12)
            }
            amount.text = card.amount.toString()
            cardName.text = card.name
            owner.text = card.owner
            cardNumber.text = formattedText
            expireData.text = "${card.expiredMonth}/$exYear"
            cardImportant.visibility = if (card.isVisible == true) View.VISIBLE else View.INVISIBLE
            cardLogo.setImageResource(determineCardType(card.pan))
            cardPhoto.setImageResource(AppHardcodeData.cardStyleImages[card.themeType ?: 0])


        }
    }

    fun determineCardType(cardNumber: String?): Int {
        val card = cardNumber?.substring(0, 4)
        return when (card) {
            "8600", "5614" -> R.drawable.img_uzcard_logo
            "9860" -> R.drawable.img_humo_logo
            else -> R.drawable.ic_cards
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            MyCardsItemRecycleViewBinding.inflate(
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
