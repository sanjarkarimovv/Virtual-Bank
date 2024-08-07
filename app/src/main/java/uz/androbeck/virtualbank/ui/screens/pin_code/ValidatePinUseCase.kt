package uz.androbeck.virtualbank.ui.screens.pin_code

import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class ValidatePinUseCase @Inject constructor(
    private val prefsProvider: PreferencesProvider
) {
    fun isValidPin(pin: String): Boolean {
        return prefsProvider.pinCode == pin
    }
}