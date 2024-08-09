package uz.androbeck.virtualbank.ui.screens.choose_language

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseLanguageViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : BaseViewModel() {


}