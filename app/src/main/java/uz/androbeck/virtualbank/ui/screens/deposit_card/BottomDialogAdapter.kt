package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemChooseCardBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

@Suppress("DEPRECATION")
class BottomDialogAdapter(
    private val cards: List<CardUIModel>,
    private val onClickListener: (CardUIModel,Boolean) -> Unit
) : RecyclerView.Adapter<BottomDialogAdapter.ViewHolder>() {
    private var selectedPosition = -1
    @Inject
    lateinit var preferencesProvider: PreferencesProvider
    inner class ViewHolder(
        val binding: ItemChooseCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getCardsUiModel: CardUIModel)= with(binding) {
            tvCardName.text = getCardsUiModel.name
            tvCardBalance.text = getCardsUiModel.amount.toString()
            tvCardNumber.text = getCardsUiModel.pan
            chooseCard.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    if (selectedPosition!=-1 && selectedPosition!=adapterPosition){
                        notifyItemChanged(selectedPosition)
                    }
                    selectedPosition=adapterPosition
                    onClickListener(getCardsUiModel,chooseCard.isChecked)
                }
                else{
                    if (selectedPosition==adapterPosition){
                        selectedPosition=-1
                    }
                }
            }
            root.setOnClickListener {
                chooseCard.isChecked=true
                onClickListener(getCardsUiModel,chooseCard.isChecked)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChooseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return cards.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position])
        holder.binding.chooseCard.isChecked=position==selectedPosition
    }
}