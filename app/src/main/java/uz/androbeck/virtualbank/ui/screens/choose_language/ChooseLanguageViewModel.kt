package uz.androbeck.virtualbank.ui.screens.choose_language

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import javax.inject.Inject

@HiltViewModel
class ChooseLanguageViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : BaseViewModel() {

    private val _language = Channel<Language>()
    val language = _language.receiveAsFlow()

    fun setLanguage(language: String) = viewModelScope.launch {
        if (language != preferencesProvider.language)
            preferencesProvider.language = language
        _language.send(preferencesProvider.language.getLanguageByCode())
    }
}