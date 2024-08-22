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
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    }

