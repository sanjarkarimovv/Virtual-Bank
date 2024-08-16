package uz.androbeck.virtualbank.utils.extentions

import uz.androbeck.virtualbank.ui.enums.Theme

fun Int.getThemeByCode() = when (this) {
    Theme.LIGHT.code -> Theme.LIGHT
    Theme.DARK.code -> Theme.DARK
    Theme.SYSTEM.code -> Theme.SYSTEM
    else -> Theme.SYSTEM
}