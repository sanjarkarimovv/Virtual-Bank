package uz.androbeck.virtualbank.ui

import android.widget.ImageView
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.home.SnackBarContainerModel
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

    fun setNavGraphEvent(event: NavGraphEvent) = viewModelScope.launch {
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

    fun saveBiometricPref(boolean: Boolean){
        prefsProvider.useBiometric = boolean && prefsProvider.useBiometric
    }

    fun checkIsAwayLong() = viewModelScope.launch {
        if (prefsProvider.useIsAwayLong) {
            val timeAway = System.currentTimeMillis() - prefsProvider.isAwayLong
            val isAwayTooLong = timeAway > prefsProvider.awayLongTime * 1000
            val hasValidToken = prefsProvider.accessToken.isNotEmpty()

            isAwayLong.send(isAwayTooLong && hasValidToken)
        }
    }

    fun observeIsAwayLong() : Flow<Boolean> {
        return isAwayLong.consumeAsFlow().share(viewModelScope)
    }

    fun observeNavGraphEvent(): Flow<NavGraphEvent> {
        return navGraphEvent.consumeAsFlow().share(viewModelScope)
    }


}