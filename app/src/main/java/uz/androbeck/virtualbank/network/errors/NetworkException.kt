package uz.androbeck.virtualbank.network.errors

class NetworkException(override val message: String?, override val cause: Throwable?) : Exception()