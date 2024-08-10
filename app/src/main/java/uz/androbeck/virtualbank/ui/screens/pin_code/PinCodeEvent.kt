package uz.androbeck.virtualbank.ui.screens.pin_code

interface PinCodeEvent {
    data class PinRegistered(val pin: String) : PinCodeEvent
    data object PinValidated : PinCodeEvent
    data object PinValidationFailed : PinCodeEvent
}
