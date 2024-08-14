package uz.androbeck.virtualbank.utils

import androidx.appcompat.app.AppCompatDelegate

object Constants {
    object FileName {
        const val SHARED_PREFS = "virtual_bank"
        const val DATABASE_NAME = "virtual_bank_database"
    }

    object Endpoint {
        const val SIGN_UP = "auth/sign-up"
        const val SIGN_IN_VERIFY = "auth/sign-in/verify"

        const val SIGN_IN = "auth/sign-in"
        const val SIGN_UP_VERIFY = "auth/sign-up/verify"
        const val UPDATE_TOKEN = "auth/update-token"
        const val FULL_INFO = "/home/user-info/details"
        const val UPDATE_INFO = "home/user-info"
        const val BASIC_INFO = "/home/user-info"
        const val SIGN_IN_RESEND = "auth/sign-in/resend"
        const val SIGN_UP_RESEND = "auth/sign-up/resend"
        const val LAST_TRANSFERS = "home/last-transfers"
        const val TOTAL_BALANCE = "home/total-balance"
        const val GET_HISTORY = "transfer/transfer"
    }

    object Header {
        const val TOKEN_TITLE = "Authorization"
        const val ACCEPT_TITLE = "accept"
        const val APPLICATION_JSON_VALUE = "application/json"
        const val TOKEN_TYPE = "Bearer"
    }

    object ArgumentKey {
        const val TOKEN_FOR_VERIFY = "token_for_verify"
        const val PHONE_NUMBER_FOR_VERIFY = "phone_number_for_verify"
        const val SCREEN = "screen"
    }

    object String {
        const val EMPTY = ""
    }

    object Localization {
        const val LANGUAGE = "language"
        const val UZBEK = "uz"
        const val ENGLISH = "en"
        const val RUSSIAN = "ru"
    }

    object Number {
        const val FIVE_THOUSAND = 5000L
        const val ONE = 1
        const val reENTRY_GET_CODE_TIME = 180000L
    }

    object Theme {
        const val LIGHT_MODE = AppCompatDelegate.MODE_NIGHT_NO
        const val DARK_MODE = AppCompatDelegate.MODE_NIGHT_YES
        const val SYSTEM = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

    }
}