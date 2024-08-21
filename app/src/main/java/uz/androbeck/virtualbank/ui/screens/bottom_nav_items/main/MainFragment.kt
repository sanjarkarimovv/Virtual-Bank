package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    override fun setup() {
            binding.root.singleClickable{
                Toast.makeText(requireActivity(), "ishla", Toast.LENGTH_SHORT).show()
                val transferUIModel= TransferUIModel(
                    "third-card",
                    "7",
                    "1234567898765432",
                    100100
                )
                viewModel.getTransfer(transferUIModel)
            }
    }
}