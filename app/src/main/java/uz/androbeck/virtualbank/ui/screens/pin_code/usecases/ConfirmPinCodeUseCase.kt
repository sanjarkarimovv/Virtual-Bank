package uz.androbeck.virtualbank.ui.screens.pin_code.usecases

import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class ConfirmPinCodeUseCase @Inject constructor(
    private val prefsProvider: PreferencesProvider
) {
    fun confirmPinCode(pin: String): Boolean {
        return prefsProvider.pinCodeReserve == pin
    }
}