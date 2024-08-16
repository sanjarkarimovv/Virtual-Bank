package uz.androbeck.virtualbank.ui.dialogs.change_theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.enums.Theme
import uz.androbeck.virtualbank.utils.extentions.getThemeByCode
import uz.androbeck.virtualbank.utils.extentions.share
import javax.inject.Inject


@HiltViewModel
class ChangeThemeViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider,
) : ViewModel() {

    private val _theme = Channel<Theme>()
    val theme = _theme.consumeAsFlow().share(viewModelScope)

    fun setTheme(theme: Int) = viewModelScope.launch {
        if (theme != preferencesProvider.theme)
            preferencesProvider.theme = theme
        _theme.send(preferencesProvider.theme.getThemeByCode())
    }
}