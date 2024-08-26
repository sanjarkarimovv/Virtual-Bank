package uz.androbeck.virtualbank.ui.screens.notification

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentNotificationBinding
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationTransferUIModel
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.notification_info.NotificationInfoBottomDialog
import uz.androbeck.virtualbank.ui.screens.notification.events.NotificationTransferUiEvent
import uz.androbeck.virtualbank.ui.screens.notification.events.NotificationUiEvent
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import javax.inject.Inject

@AndroidEntryPoint
class NotificationFragment : BaseFragment(R.layout.fragment_notification) {
    private val binding by viewBinding(FragmentNotificationBinding::bind)
    private lateinit var adapter: NotificationAdapter
    private val vm: NotificationViewModel by viewModels()

    @Inject
    lateinit var messageController: MessageController

    override fun setup() {
        with(binding) {
            adapter = NotificationAdapter(
                requireContext(),
                onClickTransferMoreBtn = { dto ->
                    showNotificationInfoDialog(
                        notificationTransferUIModel = dto,
                        notificationUIModel = null
                    )
                },
                onClickTransferOpen = {
                },
                onClickNotificationMoreBtn = { dto ->
                    showNotificationInfoDialog(
                        notificationTransferUIModel = null,
                        notificationUIModel = dto
                    )
                },
                onClickNotificationOpen = { dto ->
                    dto.navigationType?.let { toNavigation(it) } ?: toast("")
                }
            )
            vm.getTransfers()
            vm.fetchNotifications()
            rvNotification.adapter = adapter
        }
    }

    override fun clicks() = with(binding) {
        toolbar.onClickLeftIcon = {
            findNavController().popBackStack()
        }
        flSwap.setOnRefreshListener {
            vm.getTransfers()
            vm.fetchNotifications()
        }
    }

    private fun showNotificationInfoDialog(
        notificationTransferUIModel: NotificationTransferUIModel?,
        notificationUIModel: NotificationUIModel?,
    ) {
        NotificationInfoBottomDialog(
            toNavigation = {
                toNavigation(it)
            },
            notificationTransferUIModel,
            notificationUIModel
        ).show(
            childFragmentManager,
            NotificationInfoBottomDialog::class.java.name
        )
    }

    override fun observe() {
        vm.lastTransfers.observe(viewLifecycleOwner) { event ->
            when (event) {
                NotificationTransferUiEvent.Loading -> {
                    binding.progressBar.visible()
                    binding.rvNotification.gone()
                }

                is NotificationTransferUiEvent.Success -> {
                    val successList =
                        event.list.filter { type -> type.type == Constants.String.INCOME }
                    val combinedList = (adapter.currentList + successList).distinctBy { it.time }
                        .sortedByDescending { it.time }

                    if (combinedList.isEmpty()) {
                        binding.progressBar.visible()
                        binding.rvNotification.gone()
                    } else {
                        binding.progressBar.gone()
                        binding.rvNotification.visible()
                        adapter.submitList(combinedList)
                        binding.flSwap.isRefreshing = false
                    }
                }
            }
        }

        vm.notifications.observe(viewLifecycleOwner) { notifications ->
            val sortedNotifications = notifications.sortedByDescending { it.time }
            adapter.submitList(sortedNotifications)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.uiEvent.collect { event ->
                when (event) {
                    is NotificationUiEvent.Loading -> {
                        binding.progressBar.visible()
                        binding.rvNotification.gone()
                    }

                    is NotificationUiEvent.Success -> {
                        val sortedList = event.list
                        val combinedList = (adapter.currentList + sortedList).distinctBy { it.time }
                            .sortedByDescending { it.time }
                        adapter.submitList(combinedList)
                        binding.progressBar.gone()
                        binding.rvNotification.visible()
                        binding.flSwap.isRefreshing = false
                    }

                    is NotificationUiEvent.Error -> {
                        binding.progressBar.gone()
                        binding.rvNotification.gone()
                    }
                }
            }
        }

        messageController.observeMessage().onEach {
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun toNavigation(type: Int) {
        when (type) {
            1 -> {
                val playMarketIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.String.TO_PLAY_STORE_URL)
                )
                startActivity(playMarketIntent)
            }
            2 -> findNavController().navigate(R.id.action_notificationFragment_to_transferFragment)
            3 -> findNavController().navigate(R.id.action_notificationFragment_to_paymentFragment)
        }
    }
}