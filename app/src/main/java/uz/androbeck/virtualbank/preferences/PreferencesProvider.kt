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
    var language: String by preferences.string(Constants.Localization.UZBEK)
    var theme: Int by preferences.int(Constants.Theme.SYSTEM_MODE)
    var radioButtonLight: Boolean by preferences.boolean()
    var radioButtonDark: Boolean by preferences.boolean()
    var radioButtonSystem: Boolean by preferences.boolean()

    fun clear() {
        preferences.edit().clear().apply()
    }
}
