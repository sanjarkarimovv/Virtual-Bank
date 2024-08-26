package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemChooseCardBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel

@Suppress("DEPRECATION")
class BottomDialogAdapter(
    private val cards: List<CardUIModel>,
    context: Context,
    private val onClickListener: (CardUIModel) -> Unit
) : RecyclerView.Adapter<BottomDialogAdapter.ViewHolder>() {
    private val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private var selectedPosition = sharedPreferences.getInt("selected_position", -1)
    inner class ViewHolder(
        val binding: ItemChooseCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getCardsUiModel: CardUIModel)= with(binding) {
            tvCardName.text = getCardsUiModel.name
            tvCardBalance.text = getCardsUiModel.amount.toString()
            tvCardNumber.text = getCardsUiModel.pan
            chooseCard.isChecked=selectedPosition==adapterPosition
            chooseCard.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    if (selectedPosition!=-1 && selectedPosition!=adapterPosition){
                           notifyItemChanged(selectedPosition)
                    }
                    selectedPosition=adapterPosition
                    val editor = sharedPreferences.edit()
                    editor.putInt("selected_position", selectedPosition)
                    editor.putBoolean("switch_state_${getCardsUiModel.id}", true)
                    editor.apply()
                }
                else{
                    if (selectedPosition==adapterPosition){
                        selectedPosition=-1
                    }
                }
                onClickListener(getCardsUiModel)
            }
            root.setOnClickListener {
                chooseCard.isChecked=true
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
    }
}