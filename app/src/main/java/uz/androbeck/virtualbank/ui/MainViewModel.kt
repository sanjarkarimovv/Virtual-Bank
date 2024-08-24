package uz.androbeck.virtualbank.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.utils.extentions.share
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider,
) : BaseViewModel() {

    private val navGraphEvent = Channel<NavGraphEvent>()

    private val isAwayLong = Channel<Boolean>()

    private val _openHistoryItem = Channel<Unit>()
    val openHistoryItem = _openHistoryItem.consumeAsFlow().share(viewModelScope)

    fun openHistoryItem() = viewModelScope.launch {
        _openHistoryItem.send(Unit)
    }

    fun setNavGraphEvent(event: NavGraphEvent) = viewModelScope.launch {
        delay(500L)
        navGraphEvent.send(event)
    }

    fun setNavGraphEvent() = viewModelScope.launch {
        if (prefsProvider.accessToken.isNotEmpty()) {
            navGraphEvent.send(NavGraphEvent.PinCode)
        } else {
            navGraphEvent.send(NavGraphEvent.Auth)
        }
    }

    fun saveAwayLong() {
        prefsProvider.isAwayLong = System.currentTimeMillis()
    }

    fun checkIsAwayLong() = viewModelScope.launch {
        isAwayLong.send((System.currentTimeMillis() - prefsProvider.isAwayLong) > 20000 && prefsProvider.accessToken.isNotEmpty())
    }

    fun observeIsAwayLong() : Flow<Boolean> {
        return isAwayLong.consumeAsFlow().share(viewModelScope)
    }

    fun observeNavGraphEvent(): Flow<NavGraphEvent> {
        return navGraphEvent.consumeAsFlow().share(viewModelScope)
    }
}