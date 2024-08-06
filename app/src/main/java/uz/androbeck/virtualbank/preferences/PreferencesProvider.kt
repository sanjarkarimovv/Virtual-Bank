package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesProvider @Inject constructor(
    private val preferences: SharedPreferences
) {
    var token: String by preferences.string()
    var pinCode: String by preferences.string()
    var errorAttempts: Int by preferences.int()
    var lastErrorTimestamp: Long by preferences.long()

    fun clear() {
        preferences.edit().clear().apply()
    }
}
