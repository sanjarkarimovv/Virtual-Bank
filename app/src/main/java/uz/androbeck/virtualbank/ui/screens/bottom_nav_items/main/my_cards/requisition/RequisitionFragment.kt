package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards.requisition

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMyCardsBinding
import uz.androbeck.virtualbank.databinding.FragmentRequisitionBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

class RequisitionFragment:BaseFragment(R.layout.fragment_requisition) {
    private val binding: FragmentRequisitionBinding by viewBinding()

    override fun setup() {
binding.toolbar.onClickLeftIcon={
    findNavController().popBackStack()
}


    }
}