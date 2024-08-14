package uz.androbeck.virtualbank.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
    private val prefsProvider: PreferencesProvider
) : BaseViewModel() {

    private val navGraphEvent = Channel<NavGraphEvent>()

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

    fun observeNavGraphEvent(): Flow<NavGraphEvent> {
        return navGraphEvent.consumeAsFlow().share(viewModelScope)
    }
}