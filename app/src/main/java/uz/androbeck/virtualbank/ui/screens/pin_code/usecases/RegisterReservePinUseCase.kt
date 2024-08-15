package uz.androbeck.virtualbank.ui.screens.pin_code.usecases

import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class RegisterReservePinUseCase @Inject constructor(
    private val prefsProvider: PreferencesProvider
) {
    fun registerReservePin(pin: String) {
        prefsProvider.pinCodeReserve = pin
    }
}