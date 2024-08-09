package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Inject

class PreferencesProvider @Inject constructor(
    private val preferences: SharedPreferences
) {
    var token: String by preferences.string()
    var pinCode: String by preferences.string()
    var errorAttempts: Int by preferences.int()
    var lastErrorTimestamp: Long by preferences.long()
    var refreshToken: String by preferences.string()
    var autoBlockTime:Long by preferences.long()
    var biometricLogin: Boolean by preferences.boolean()
    var paymentConfirmation: Boolean by preferences.boolean()
    var autoBlockIsOn: Boolean by preferences.boolean()
    var useIsGeolocation: Boolean by preferences.boolean()
    var language: String by preferences.string(Constants.Localization.UZBEK)

    fun clear() {
        preferences.edit().clear().apply()
    }
}
