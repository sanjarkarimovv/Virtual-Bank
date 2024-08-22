package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    override fun setup() {
            binding.root.singleClickable{

                findNavController().navigate(R.id.action_mainFragment_to_addCardFragment)
                }
    }
}