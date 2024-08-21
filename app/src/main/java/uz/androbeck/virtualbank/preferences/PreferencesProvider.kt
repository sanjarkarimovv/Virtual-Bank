package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Inject

class PreferencesProvider @Inject constructor(
    private val preferences: SharedPreferences
) {
    var accessToken: String by preferences.string()
    var pinCodeReserve : String by preferences.string()
    var pinCode: String by preferences.string()
    var errorAttempts: Int by preferences.int()
    var useIsAwayLong : Boolean by preferences.boolean(true)
    var awayLongTime : Int by preferences.int(10)
    var isAwayLong : Long by preferences.long()
    var refreshToken: String by preferences.string()
    var useBiometric: Boolean by preferences.boolean()
    var useBiometricPayment : Boolean by preferences.boolean(false)
    var language: String by preferences.string(Constants.Localization.UZBEK)

    fun clear() {
        preferences.edit().clear().apply()
    }
}
