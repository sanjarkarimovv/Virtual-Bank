package uz.androbeck.virtualbank.ui.screens.pin_code.events

interface PinCodeEvent {
    data object PinRegistered : PinCodeEvent
    data object PinValidated : PinCodeEvent
    data object PinValidationFailed : PinCodeEvent
}
