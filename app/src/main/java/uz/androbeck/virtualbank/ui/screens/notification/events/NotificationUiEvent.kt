package uz.androbeck.virtualbank.ui.screens.notification.events

import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel

sealed class NotificationUiEvent {
    data class Success(val list: List<NotificationUIModel>) : NotificationUiEvent()
    data object Loading : NotificationUiEvent()
    data class Error(val error: Throwable) : NotificationUiEvent()
}