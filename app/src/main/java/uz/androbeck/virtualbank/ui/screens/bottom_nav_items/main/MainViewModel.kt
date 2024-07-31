package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
) : ViewModel() {

    fun getUserFullInfo(){
        getFullInfoUseCase.invoke().onEach {
            println(it)
        }.launchIn(viewModelScope)
    }
}