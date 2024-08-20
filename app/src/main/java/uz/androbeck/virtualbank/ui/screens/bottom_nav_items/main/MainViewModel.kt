package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.transfer.TransferUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val putUpdateInfoUseCase: PutUpdateInfoUseCase,
    private val transferUseCase: TransferUseCase,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _transfer = MutableLiveData<TransferUIModel>()
    val transfer get() = _transfer

    fun getTransfer(transferUIModel: TransferUIModel) = transferUseCase(transferUIModel)


    init {
        viewModelScope.launch (Dispatchers.IO){
            getTransfer(
                TransferUIModel(
                    "third-card",
                    "7",
                    "1234567898765432",
                    100100
                )
            ).onEach {
                viewModelScope.launch {

                println(it.token)
                }
            }.catch {
                errorHandler.handleError(it)
            }.launchIn(this)
        }
    }
}
