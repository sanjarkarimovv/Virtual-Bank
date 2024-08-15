package uz.androbeck.virtualbank.ui.dialogs.change_language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import uz.androbeck.virtualbank.utils.extentions.share
import javax.inject.Inject


@HiltViewModel
class ChangeLanguageViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider,
) : ViewModel() {

    private val _language = Channel<Language>()
    val language = _language.consumeAsFlow().share(viewModelScope)

    fun setLanguage(language: String) = viewModelScope.launch {
        if (language != preferencesProvider.language)
            preferencesProvider.language = language
        _language.send(preferencesProvider.language.getLanguageByCode())
    }
}