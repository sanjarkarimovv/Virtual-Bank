package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    override fun setup() {
        setRv()
        binding.llSwipe.setOnRefreshListener {
            viewModel.getUiData()
        }
        binding.btnSettings.setOnClickListener {
            // navigate to settings screen
            findNavController().navigate(R.id.action_mainFragment_to_widgetSettingsFragment)
        }
    }

    override fun observe() {
        viewModel.homeComponents.observe(this) {
            when (it) {
                // get all components for room data base and show ui custom ui 2
                is HomeComponentsUiEvent.ComponentForUi -> {
                    binding.customBody.isShow(it.list)
                }
            }
        }
        viewModel.uiData.observe(this) {
            with(binding) {
                llSwipe.isRefreshing = false
                when (it) {
                    is HomeBodyModels.Card -> {
                        customBody.cardsAdapter.submitList(it.data)
                    }

                    is HomeBodyModels.LastTransfer -> {
                        customBody.lastTransferAdapter.submitList(it.data)
                    }

                    is HomeBodyModels.Payment -> {
                        customBody.paymentsAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun setRv() = with(binding) {
        btnSettings.setOnClickListener {
            toast("Update")
            viewModel.updateComponent(1, true)
        }
    }

}