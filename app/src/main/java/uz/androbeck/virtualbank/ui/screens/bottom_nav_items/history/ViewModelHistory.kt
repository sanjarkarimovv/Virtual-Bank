/*
package uz.androbeck.virtualbank.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.domain.useCases.history.GetHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class ViewModelHistory @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {


    val historyFlow: Flow<PagingData<InComeAndOutComeUIModel>> =
        getHistoryUseCase().cachedIn(viewModelScope).also { flow ->
            viewModelScope.launch {
                flow.collect { pagingData ->
                    Log.d("ViewModelHistory", "Data received: $pagingData")
                }
            }
        }

}
*/
