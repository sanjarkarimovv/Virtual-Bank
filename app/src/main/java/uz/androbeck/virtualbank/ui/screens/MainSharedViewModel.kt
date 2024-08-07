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

    private val _isAwayLong = MutableStateFlow<Boolean?>(null)
    val isAwayLong: StateFlow<Boolean?> get() = _isAwayLong

    fun setNavGraphEvent(event: NavGraphEvent) {
        _navGraphEvent.value = event
    }

    fun setNavGraphEvent() {
        if (prefsProvider.token.isNotEmpty()) {
            _navGraphEvent.value = NavGraphEvent.PinCode
        } else {
            _navGraphEvent.value = NavGraphEvent.Auth
        }
    }

    fun saveAwayLong() {
        prefsProvider.isAwayLong = System.currentTimeMillis()
    }

    fun checkIsAwayLong(){
        _isAwayLong.value = (System.currentTimeMillis() - prefsProvider.isAwayLong) > 20000
    }
}