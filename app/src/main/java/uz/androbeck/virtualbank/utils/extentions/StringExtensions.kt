package uz.androbeck.virtualbank.utils.extentions

import uz.androbeck.virtualbank.ui.enums.Language

fun String.getLanguageByCode(): Language = when (this) {
    Language.UZBEK.code -> Language.UZBEK
    Language.RUSSIAN.code -> Language.RUSSIAN
    Language.ENGLISH.code -> Language.ENGLISH
    else -> Language.UZBEK
}