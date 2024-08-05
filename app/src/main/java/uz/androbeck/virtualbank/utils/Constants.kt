package uz.androbeck.virtualbank.utils

object Constants {
    object FileName {
        const val SHARED_PREFS = "virtual_bank"
        const val DATABASE_NAME = "virtual_bank_database"
    }

    object Endpoint {
        const val SIGN_UP = "auth/sign-up"
        const val SIGN_UP_VERIFY = "auth/sign-up/verify"
        const val FULL_INFO = "/home/user-info/details"
    }

    object Header {
        const val TOKEN_TITLE = "Authorization"
        const val ACCEPT_TITLE = "accept"
        const val APPLICATION_JSON_VALUE = "application/json"
        const val TOKEN_TYPE = "Bearer"
    }
}