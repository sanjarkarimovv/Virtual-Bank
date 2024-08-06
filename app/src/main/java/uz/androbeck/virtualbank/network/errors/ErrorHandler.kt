package uz.androbeck.virtualbank.network.errors

interface ErrorHandler {

    fun handleError(error: Throwable)
}