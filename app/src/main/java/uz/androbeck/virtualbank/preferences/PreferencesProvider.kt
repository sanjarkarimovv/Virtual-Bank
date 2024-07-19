package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesProvider @Inject constructor(
    private val preferences: SharedPreferences
) {
    var token: String by preferences.string()

    fun clear() {
        preferences.edit().clear().apply()
    }
}
