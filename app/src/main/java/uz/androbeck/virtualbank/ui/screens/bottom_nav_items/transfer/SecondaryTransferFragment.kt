package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSecondaryTransferBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class SecondaryTransferFragment : BaseFragment(R.layout.fragment_secondary_transfer) {
    private val binding by viewBinding(FragmentSecondaryTransferBinding::bind)
    override fun setup() {
        binding.ivBack.setOnClickListener {

            findNavController().navigate(R.id.action_secondaryTransferFragment_to_transferFragment)
        }



    }

}