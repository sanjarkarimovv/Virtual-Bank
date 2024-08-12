package uz.androbeck.virtualbank.ui.screens.pin_code.usecases

import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class RegisterPinUseCase @Inject constructor(
    private val prefsProvider: PreferencesProvider
) {
    fun registerPin(pin: String) {
        prefsProvider.pinCode = pin
    }
}