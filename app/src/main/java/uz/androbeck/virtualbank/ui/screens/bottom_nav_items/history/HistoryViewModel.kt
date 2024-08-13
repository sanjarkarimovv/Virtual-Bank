package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.source.remote.history.HistoryPagingSource
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.utils.Constants

class HistoryViewModel : ViewModel() {

    private var totalAmountIncome: Float = 0f
    private var totalAmountOutcome: Float = 0f

    val historyItems: Flow<PagingData<HistoryItem>> = Pager(PagingConfig(pageSize = 5)) {
        HistoryPagingSource()
    }.flow.cachedIn(viewModelScope)


    fun getAmounts(): Pair<Float?, Float?> {
        HistoryPagingSource().response.forEach {
            it.child.forEach { child ->
                val amountIncome: Float =
                    (child.amount.takeIf { Constants.History.INCOME == child.type } ?: 0).toFloat()
                val amountOutcome: Float =
                    (child.amount.takeIf { Constants.History.OUTCOME == child.type } ?: 0).toFloat()
                totalAmountIncome = totalAmountIncome.plus(amountIncome)
                totalAmountOutcome = totalAmountOutcome.plus(amountOutcome)
                println("totalAmountIncome: $totalAmountIncome ")

            }
        }
        return Pair(totalAmountIncome, totalAmountOutcome)
    }

}


