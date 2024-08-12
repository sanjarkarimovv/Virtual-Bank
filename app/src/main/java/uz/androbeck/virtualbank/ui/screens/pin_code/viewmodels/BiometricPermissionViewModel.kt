package uz.androbeck.virtualbank.ui.screens.pin_code.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

@HiltViewModel
class BiometricPermissionViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
) : ViewModel() {
    fun setBiometrics(useBiometric: Boolean) {
        prefsProvider.useBiometric = useBiometric
    }
}