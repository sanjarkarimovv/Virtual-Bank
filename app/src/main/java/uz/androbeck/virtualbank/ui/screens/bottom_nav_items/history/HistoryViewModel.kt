package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.domain.ui_models.history.TransfersUIModel
import uz.androbeck.virtualbank.domain.useCases.history.GetHistoryUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.utils.extentions.toStartOfDay
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : BaseViewModel() {

    private var totalAmountIncome: Float = 0f
    private var totalAmountOutcome: Float = 0f

    private val _response = MutableLiveData<PagingData<HistoryItem>>()
    val response: LiveData<PagingData<HistoryItem>> get() = _response

    init {
        getHistory()
    }

    fun getHistory() {
        getHistoryUseCase.invoke()
            .map { pagingData ->
                pagingData.map { dto ->
                    mapDtoToHistoryItem(dto)
                }
            }
            .cachedIn(viewModelScope)
            .onEach {
                _response.value = it
                println(":::::::;HistoryList $it")
            }
            .launchIn(viewModelScope)
    }

    private fun mapDtoToHistoryItem(dto: TransfersUIModel): HistoryItem {
        val items = mutableListOf<HistoryItem>()
        var lastHeaderTime: Long? = null

        if (dto.transferUIModel.isNullOrEmpty()) {
            println(":::::::;HistoryList null")
        }
        dto.transferUIModel?.forEach { child ->
            println()
            val currentHeaderTime = child.time.toStartOfDay()

            if (lastHeaderTime == null || lastHeaderTime != currentHeaderTime) {
                items.add(HistoryItem.Header(currentHeaderTime))
                lastHeaderTime = currentHeaderTime
            }

            items.add(HistoryItem.Content(child))
        }

        return items.last()
    }
}
