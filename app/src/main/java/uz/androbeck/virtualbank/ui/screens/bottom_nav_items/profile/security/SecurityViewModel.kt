package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.enums.SecuritySettingKey
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.models.SecuritySettings
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
): ViewModel() {

    private val _securitySettings = MutableLiveData<SecuritySettings>()
    val securitySettings : LiveData<SecuritySettings> get() = _securitySettings

    init{
        _securitySettings.value = loadSecuritySettings()
    }

    private fun loadSecuritySettings(): SecuritySettings {
        return SecuritySettings(
            useBiometric = prefsProvider.useBiometric,
            useBiometricPayment = prefsProvider.useBiometricPayment,
            useIsAwayLong = prefsProvider.useIsAwayLong,
            awayLongTime = prefsProvider.awayLongTime
        )
    }

    fun updateSetting(key: SecuritySettingKey, value: Any) {
        when (key) {
            SecuritySettingKey.USE_BIOMETRIC -> {
                prefsProvider.useBiometric = value as Boolean
            }
            SecuritySettingKey.USE_BIOMETRIC_PAYMENT -> {
                prefsProvider.useBiometricPayment = value as Boolean
            }
            SecuritySettingKey.USE_IS_AWAY_LONG -> {
                prefsProvider.useIsAwayLong = value as Boolean
            }
            SecuritySettingKey.AWAY_LONG_TIME -> {
                prefsProvider.awayLongTime = value as Int
            }
        }
        _securitySettings.value = loadSecuritySettings()
    }
}