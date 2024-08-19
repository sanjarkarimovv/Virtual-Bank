package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.transfer.TransferUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val putUpdateInfoUseCase: PutUpdateInfoUseCase,
    private val transfersUseCase: TransferUseCase,
) : ViewModel() {
    fun aa() {
        println("start")
        transfersUseCase.invoke(
            uiReqModel = TransferUIModel(
                "third-card",
                "7",
                "1234567898765432",
                100100.0
            )
        ).onEach {

            println(it.token)
        }.catch {
            println(it.message)
        }
            .launchIn(viewModelScope)
    }

}