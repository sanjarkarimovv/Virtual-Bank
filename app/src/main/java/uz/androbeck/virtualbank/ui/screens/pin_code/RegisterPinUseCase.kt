package uz.androbeck.virtualbank.ui.screens.pin_code

import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class RegisterPinUseCase @Inject constructor(
    private val prefsProvider: PreferencesProvider
) {
    fun registerPin(pin: String) {
        if(prefsProvider.pinCodeReserve.isEmpty()){
            prefsProvider.pinCode = pin
        } else {
            prefsProvider.pinCodeReserve = pin
        }
    }
}