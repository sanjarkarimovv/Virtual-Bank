package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import uz.androbeck.virtualbank.ui.enums.Theme
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
    var theme: Int by preferences.int(Constants.Theme.SYSTEM)

    fun clear() {
        preferences.edit().clear().apply()
    }
}
