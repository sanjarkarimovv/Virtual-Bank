package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

sealed class Event {
    data class Success(val message: String? = null) : Event()
    data class Error(val message: String? = null) : Event()
}