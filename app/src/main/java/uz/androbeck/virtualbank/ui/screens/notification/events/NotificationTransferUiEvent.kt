package uz.androbeck.virtualbank.ui.screens.notification.events

import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationTransferUIModel

sealed class NotificationTransferUiEvent {
    data class Success(val list: List<NotificationTransferUIModel>) : NotificationTransferUiEvent()
    data object Loading : NotificationTransferUiEvent()
}