package uz.androbeck.virtualbank.ui.screens.pin_code.events

interface ConfirmPinCodeEvent {
    data object PinValidated : ConfirmPinCodeEvent
    data object PinValidationFailed : ConfirmPinCodeEvent
}