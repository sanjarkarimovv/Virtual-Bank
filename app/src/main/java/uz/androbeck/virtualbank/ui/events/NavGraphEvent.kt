package uz.androbeck.virtualbank.ui.events

sealed interface NavGraphEvent {
    data object Auth : NavGraphEvent
    data object Main : NavGraphEvent
    data object PinCode : NavGraphEvent
    data object Security : NavGraphEvent
}