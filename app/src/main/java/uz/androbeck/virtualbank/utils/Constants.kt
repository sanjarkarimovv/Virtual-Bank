package uz.androbeck.virtualbank.utils

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
        const val GET_HISTORY="transfer/transfer"
        const val DELETE_CARD="card"
        const val ADD_CARD="card"
        const val GET_CARD="card"
        const val GET_FEE="transfer/fee"
        const val TRANSFER="transfer/transfer"
        const val TRANSFER_VERIFY="transfer/transfer/verify"
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
        const val ADD_HOME = "Uy qo'shish"
    }

    object Localization {
        const val LANGUAGE = "language"
        const val UZBEK = "uz"
        const val ENGLISH = "en"
        const val RUSSIAN = "ru"
    }

    object Number{
        const val FIVE_THOUSAND = 5000L
        const val ONE = 1
        const val reENTRY_GET_CODE_TIME = 180000L
        const val OFFSCREEN_PAGE_LIMIT = 1
        const val SELECT_CARD_STROKE_WIDTH = 2
        const val DEFAULT_CARD_STROKE_WIDTH = 1
    }
}