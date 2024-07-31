package uz.androbeck.virtualbank.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
) : BaseViewModel() {

    fun getToken() = prefsProvider.token
}

// Presentation <-> ViewModel <-> UseCase <-> Repository <-> DataSource -> Local And Remote