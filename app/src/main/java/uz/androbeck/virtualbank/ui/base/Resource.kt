package uz.androbeck.virtualbank.ui.base

sealed class Resource {
    data object Loading : Resource()
    data class Success<T>(val data: T) : Resource()
}