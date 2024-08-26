package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.map.Mapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.card.DeleteCardReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.customViews.forHome.HeaderUiEvent
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
   @Inject
   lateinit var messageController: MessageController
    override fun setup() {
        onClick()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUiData()
    }


     fun onClick() = with(binding) {
        llSwipe.setOnRefreshListener {
            viewModel.getUiData()
        }
        btnSettings.setOnClickListener {
            // navigate to settings screen
            findNavController().navigate(R.id.action_mainFragment_to_widgetSettingsFragment)
        }
         toolbar.clickNotification = {

            findNavController().navigate(R.id.action_mainFragment_to_notificationFragment)
        }
        customHeader.clicks = {
            when (it) {
                HeaderUiEvent.ClickCards -> {
                    findNavController().navigate(R.id.action_mainFragment_to_myCardsFragment)
                }

                HeaderUiEvent.ClickMore -> {
                    // 3 dot
                }

                HeaderUiEvent.ClickNfs -> {
                    // click nfs
                }

                HeaderUiEvent.ClickQR -> {
                    // click qrd
                }

                is HeaderUiEvent.ClickShowAmount -> {
                    if (it.isShow) {
                        viewModel.getTotalBalance()
                    }
                }
            }
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
                        // customBody.cardsDefIconShow(it.data.isEmpty())
                        println(":::cards -> ${it.data}")
                        customBody.cardsAdapterSubmitList(it.data)
                    }

                    is HomeBodyModels.LastTransfer -> {
                        customBody.lastTransferDefIconShow(it.data.isEmpty())
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

                    is HomeBodyModels.Advertising -> {
                        it.data?.let { list ->
                            binding.customBody.advertisingAdapterLoadList(list)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("::AAAA OnDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("::AAAA OnDestroy")
    }
}