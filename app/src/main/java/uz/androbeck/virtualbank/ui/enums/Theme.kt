package uz.androbeck.virtualbank.ui.enums

import androidx.appcompat.app.AppCompatDelegate

enum class Theme(
    var code: Int
) {
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}