package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.domain.useCases.history.GetHistoryUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : BaseViewModel() {

    val response: Flow<PagingData<HistoryItem>> =
        getHistoryUseCase.invoke().onStart {
            getHistoryUseCase.resetTotals()
        }.cachedIn(viewModelScope)
    fun getAmounts(): Pair<String?, String?>{
     return   getHistoryUseCase.getTotalAmounts()
    }

}

