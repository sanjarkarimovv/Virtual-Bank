package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.useCases.main.MainUseCase
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val provider: PreferencesProvider
) : ViewModel() {

    fun getUserFullInfo(){
        mainUseCase.invoke(provider.token).onEach {
            println(it)
        }.launchIn(viewModelScope)
    }
}