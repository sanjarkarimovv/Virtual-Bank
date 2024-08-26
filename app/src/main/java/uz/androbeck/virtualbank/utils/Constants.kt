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
        const val FULL_INFO = "home/user-info/details"
        const val UPDATE_INFO = "home/user-info"
        const val BASIC_INFO = "home/user-info"
        const val SIGN_IN_RESEND = "auth/sign-in/resend"
        const val SIGN_UP_RESEND = "auth/sign-up/resend"
        const val LAST_TRANSFERS = "home/last-transfers"
        const val TOTAL_BALANCE = "home/total-balance"
        const val GET_HISTORY="transfer/history"
        const val DELETE_CARD="card"
        const val ADD_CARD="card"
        const val GET_CARD="card"
        const val GET_FEE="transfer/fee"
        const val TRANSFER="transfer/transfer"
        const val TRANSFER_VERIFY="transfer/transfer/verify"
    }
    object History {
        const val INCOME = "income"
        const val OUTCOME = "outcome"

    }

    object Header {
        const val TOKEN_TITLE = "Authorization"
        const val ACCEPT_TITLE = "accept"
        const val APPLICATION_JSON_VALUE = "application/json"
        const val TOKEN_TYPE = "Bearer"
    }

    object ArgumentKey {
        const val USER_FIRST_NAME = "user_first_name"
        const val USER_LAST_NAME = "user_last_name"
        const val USER_BORN_DATE = "user_born_date"
        const val USER_GENDER = "user_gender"
        const val TOKEN_FOR_VERIFY = "token_for_verify"
        const val PHONE_NUMBER_FOR_VERIFY = "phone_number_for_verify"
        const val SCREEN = "screen"
        const val USER_FULL_INFO = "user_full_info_key"
    }

    object String {
        const val EMPTY = ""
        const val ADD_HOME = "Uy qo'shish"
        const val FULL_CARD_NUMBER_STARS = "**** **** **** "
        const val HALF_CARD_NUMBER = "** **** "
        const val INCOME = "income"
        const val TO_PLAY_STORE_URL="https://play.google.com/store"
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
        const val OFFSCREEN_PAGE_LIMIT = 1
        const val SELECT_CARD_STROKE_WIDTH = 2
        const val DEFAULT_CARD_STROKE_WIDTH = 1
        const val NUM_NOTIFICATION_TABS = 2
        const val VIEW_TYPE_TRANSFER = 1
        const val VIEW_TYPE_GENERAL = 2
    }

    object Firebase {
        const val FIREBASE_REAL_TIME_DATABASE_URL =
            "https://virtual-bank-20dcc-default-rtdb.firebaseio.com/"
        const val FIREBASE_NOTIFICATION = "notifications"
        const val CHANNEL_ID = "uz.androbeck.virtualbank.notifications"
        const val DEFAULT_CHANNEL_NAME = "Default Channel"
    }
}