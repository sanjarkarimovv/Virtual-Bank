package uz.androbeck.virtualbank.ui.screens

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
) : BaseViewModel() {

    private val _navGraphEvent = MutableStateFlow<NavGraphEvent?>(null)
    val navGraphEvent: StateFlow<NavGraphEvent?> get() = _navGraphEvent

    fun setNavGraphEvent(event: NavGraphEvent) {
        _navGraphEvent.value = event
    }

    fun setNavGraphEvent() {
        if (prefsProvider.token.isNotEmpty()) {
            _navGraphEvent.value = NavGraphEvent.Main
        } else {
            _navGraphEvent.value = NavGraphEvent.Auth
        }
    }
}