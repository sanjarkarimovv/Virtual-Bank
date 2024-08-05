package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.home.UpdateInfoUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val putUpdateInfoUseCase: PutUpdateInfoUseCase,
) : ViewModel() {

    fun getUserFullInfo(){
        getFullInfoUseCase().onEach {
            println(it)
        }.launchIn(viewModelScope)
    }

    fun putUpdateInfo(){
       val updateInfoUIModel = UpdateInfoUIModel(
           phone = "+998971714240",
           firstName = "Sanjar",
           lastName = "Karimov",
           bornDate = "213243243243",
           gender = "1") // operator ->
       putUpdateInfoUseCase.invoke(updateInfoUIModel ).onEach {
           println(":::BBB "+it)
       }.launchIn(viewModelScope)

   }
}