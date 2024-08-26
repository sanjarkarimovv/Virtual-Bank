package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events

interface ChangePinCodeEvent {
    data object PinCheck : ChangePinCodeEvent
    data object PinSet : ChangePinCodeEvent
    data object PinValidate : ChangePinCodeEvent
    data object PinSuccess : ChangePinCodeEvent
}