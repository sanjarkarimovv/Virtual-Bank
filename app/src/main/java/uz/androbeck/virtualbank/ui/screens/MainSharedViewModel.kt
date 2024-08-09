package uz.androbeck.virtualbank.ui.screens

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
) : BaseViewModel() {

    private val navGraphEvent = Channel<NavGraphEvent>()

    private val _isAwayLong = MutableStateFlow<Boolean?>(null)
    val isAwayLong: StateFlow<Boolean?> get() = _isAwayLong
    
    fun setNavGraphEvent(event: NavGraphEvent) = viewModelScope.launch {
        navGraphEvent.send(event)
    }

    fun setNavGraphEvent() = viewModelScope.launch {
        if (prefsProvider.token.isNotEmpty()) {
            navGraphEvent.send(NavGraphEvent.PinCode)
        } else {
            navGraphEvent.send(NavGraphEvent.Auth)
        }
    }

    fun saveAwayLong() {
        prefsProvider.isAwayLong = System.currentTimeMillis()
    }

    fun checkIsAwayLong(){
        _isAwayLong.value = (System.currentTimeMillis() - prefsProvider.isAwayLong) > 20000 && prefsProvider.token.isNotEmpty()
    }

    fun observeNavGraphEvent(): Flow<NavGraphEvent> {
        return navGraphEvent.consumeAsFlow()
    }
}