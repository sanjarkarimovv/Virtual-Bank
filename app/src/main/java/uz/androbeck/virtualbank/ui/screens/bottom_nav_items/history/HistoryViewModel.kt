package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.domain.useCases.history.GetHistoryUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : BaseViewModel() {

    private var totalAmountIncome: Float = 0f
    private var totalAmountOutcome: Float = 0f

    val response: Flow<PagingData<HistoryItem>> =
        getHistoryUseCase.invoke().cachedIn(viewModelScope)
    fun getAmounts(): Pair<Long?, Long?>{
     return   getHistoryUseCase.getTotalAmounts()
    }

}
/* private val _response = MutableLiveData<PagingData<HistoryItem>>()
 val response: LiveData<PagingData<HistoryItem>> get() = _response

 init {
     getHistory()
 }

 fun getHistory() {
     getHistoryUseCase.invoke()
         .map { pagingData ->
             pagingData.map { dto ->

                 println(":::::::;HistoryGetHistory ${(dto.time.toStartOfDay())}")
                 mapDtoToHistoryItem(dto)
             }
         }
         .cachedIn(viewModelScope)
         .onEach {
             _response.value = it
             println(":::::::;Historygetresponse $it")
         }
         .launchIn(viewModelScope)
 }

 private fun mapDtoToHistoryItem(dto: InComeAndOutComeUIModel): HistoryItem {
     val items = mutableListOf<HistoryItem>()
     if (dto.type == "INCOME") {
         totalAmountIncome += dto.amount
         println(":::::::;HistoryAmountIncome $totalAmountIncome")
     } else {
         totalAmountOutcome += dto.amount
     }

         val currentHeaderTime = dto.time.toStartOfDay()
         return HistoryItem.Header(currentHeaderTime)
             .apply { items.add(this) }
             .also { items.add(HistoryItem.Content(dto)) }
     }*/


