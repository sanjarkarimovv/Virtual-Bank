package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events

interface ChangePinAnimEvent {
    data object PinValidated : ChangePinAnimEvent
    data object PinNotValidated : ChangePinAnimEvent
    data object PinNeutral : ChangePinAnimEvent
}