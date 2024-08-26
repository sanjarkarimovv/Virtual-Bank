package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards.requisition

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMyCardsBinding
import uz.androbeck.virtualbank.databinding.FragmentRequisitionBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment

@Suppress("DEPRECATION")
class RequisitionFragment : BaseFragment(R.layout.fragment_requisition) {
    private var cardNumberVisibility : Boolean? = true
    private val binding: FragmentRequisitionBinding by viewBinding()

    @SuppressLint("SetTextI18n")
    override fun setup() {
        binding.toolbar.onClickLeftIcon = {
            findNavController().popBackStack()
        }
        val cardUiModel = arguments?.getParcelable<CardUIModel>("card")
        binding.txtName.text = cardUiModel?.owner
        binding.txtNameCardValue.text = cardUiModel?.name
        binding.txtDateValue.text =cardUiModel?.expiredMonth.toString()+"/"+cardUiModel?.expiredYear.toString()
        binding.txtCardNumberValue.text =  "**********"+cardUiModel?.pan

        binding.eyeIcon.setOnClickListener {
            if (cardNumberVisibility==false){
                cardNumberVisibility=true
                binding.txtCardNumberValue.text = "**********"+cardUiModel?.pan

                binding.eyeIcon.setImageResource(R.drawable.ic_eye)
                }else{
                cardNumberVisibility=false
                binding.txtCardNumberValue.text = "**************"
                binding.eyeIcon.setImageResource(R.drawable.ic_eye_hide)
            }

        }
    }
}