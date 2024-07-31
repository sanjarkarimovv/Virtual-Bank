package uz.androbeck.virtualbank.network.errors

class UnknownException(override val message: String?, override val cause: Throwable?) : Exception()