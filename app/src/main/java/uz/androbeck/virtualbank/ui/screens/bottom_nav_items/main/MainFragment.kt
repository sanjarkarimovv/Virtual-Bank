package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.customViews.forHome.HeaderUiEvent
import uz.androbeck.virtualbank.ui.dialogs.show_cards.ShowCards
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    override fun setup() {
        setRv()
        onClick()
    }

    private fun onClick() = with(binding) {
        llSwipe.setOnRefreshListener {
            viewModel.getUiData()
        }
        btnSettings.setOnClickListener {
            // navigate to settings screen
            customBody.stopCounter()
            findNavController().navigate(R.id.action_mainFragment_to_widgetSettingsFragment)
        }
        customHeader.clicks.onEach {
            when (it) {
                HeaderUiEvent.ClickCards -> {
                    findNavController().navigate(R.id.action_mainFragment_to_addCardFragment)
                }

                HeaderUiEvent.ClickMore -> {
                    ShowCards().show(childFragmentManager, "show")
                }

                HeaderUiEvent.ClickNfs -> {

                }

                HeaderUiEvent.ClickQR -> {

                }

                is HeaderUiEvent.ClickShowAmount -> {
                    if (it.isShow) {
                        viewModel.getTotalBalance()
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

                    is HomeBodyModels.TotalBalance -> {
                        binding.customHeader.setAmount(it.amount)
                    }

                    is HomeBodyModels.Error -> {
                        toast(it.massage)
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