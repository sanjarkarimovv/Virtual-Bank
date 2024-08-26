package uz.androbeck.virtualbank.domain.useCases.history

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.domain.mapper.history.GetHistoryMapper
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.formatAmountWithSpaces
import uz.androbeck.virtualbank.utils.extentions.toStartOfDay
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val getHistoryMapper: GetHistoryMapper,
) {
    operator fun invoke(): Flow<PagingData<HistoryItem>> {
        return historyRepository.getHistory()
            .map { pagingData ->
                pagingData
                    .map { historyDto ->
                        getHistoryMapper.toUIModel(historyDto)
                    }
                    .map { uiModel ->
                        println("GetHistoryUseCase Mapping: $uiModel")
                        calculateAmounts(uiModel)
                        HistoryItem.Content(uiModel)
                    }
                    .insertSeparators { before, after ->
                        if (after == null) {
                            null
                        } else if (before == null) {
                            HistoryItem.Header(after.child.time)
                        } else if (!isSameDate(before.child.time, after.child.time)) {
                            HistoryItem.Header(after.child.time)
                        } else {
                            null
                        }
                    }
            }
    }


    private fun isSameDate(time1: Long, time2: Long): Boolean {
        // val oneDayMillis = 24 * 60 * 60 * 1000
        //  val days1 = time1 / oneDayMillis
        // val days2 = time2 / oneDayMillis
        //return days1 == days2
        return time1.toStartOfDay() == time2.toStartOfDay()
    }

    private var totalAmountIncome: Long = 0
    private var totalAmountOutcome: Long = 0

    private fun calculateAmounts(child: InComeAndOutComeUIModel) {
        val amountIncome: Long =
            (child.amount.takeIf { Constants.History.INCOME == child.type } ?: 0).toLong()
        val amountOutcome: Long =
            (child.amount.takeIf { Constants.History.OUTCOME == child.type } ?: 0).toLong()
        totalAmountIncome = totalAmountIncome.plus(amountIncome)
        totalAmountOutcome = totalAmountOutcome.plus(amountOutcome)

        return
    }

    fun resetTotals() {
        totalAmountIncome = 0
        totalAmountOutcome = 0
    }

    fun getTotalAmounts(): Pair<String?, String?> {

        return Pair(
            formatAmountWithSpaces(totalAmountOutcome),
            formatAmountWithSpaces(totalAmountIncome)
        )

    }

    fun isTotalBalanceEmpty(): Boolean {
        return totalAmountIncome == 0L && totalAmountOutcome == 0L
    }

}
