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
                       // calculateAmounts(uiModel)
                        HistoryItem.Content(uiModel)
                    }
                    // agar vaqt ozgarsa headerga vaqt oldi
                    .insertSeparators { before, after ->
                        if (after == null) {
                            // agar ohirgi element bo'lsa, header yo'q
                            null
                        } else if (before == null) {
                            // listni boshi bo'lsa
                            HistoryItem.Header(after.child.time)
                        } else if (!isSameDate(before.child.time, after.child.time)) {
                            // agar vaqtla xarxil bolmasa header ni yangilaydi
                            HistoryItem.Header(after.child.time)
                        } else {
                            null
                        }
                    }
            }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun isSameDate(time1: Long, time2: Long): Boolean {
//
//        val date1 = time1.toLocalDate()
//        val date2 = time2.toLocalDate()
//        return date1 == date2
//    }
    private fun isSameDate(time1: Long, time2: Long): Boolean {
        // 1 kun ichida millisekundlar soni: 24 * 60 * 60 * 1000
        val oneDayMillis = 24 * 60 * 60 * 1000

        // Har bir timestampdan UTC 0 bo'yicha olingan kunni hisoblash
        val days1 = time1 / oneDayMillis
        val days2 = time2 / oneDayMillis

        return days1 == days2
    }
    private var totalAmountIncome: Long = 0
    private var totalAmountOutcome: Long = 0

    private fun calculateAmounts(child:InComeAndOutComeUIModel){

                val amountIncome: Long =
                    (child.amount.takeIf { Constants.History.INCOME == child.type } ?: 0).toLong()
                val amountOutcome: Long =
                    (child.amount.takeIf { Constants.History.OUTCOME == child.type } ?: 0).toLong()
                totalAmountIncome = totalAmountIncome.plus(amountIncome)
                totalAmountOutcome = totalAmountOutcome.plus(amountOutcome)
                println("totalAmountIncome: $totalAmountIncome ")

        return
    }
    fun getTotalAmounts(): Pair<Long?, Long?>{
        return Pair(totalAmountOutcome,totalAmountIncome)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun Long.toLocalDate(): java.time.LocalDate {
//        return java.time.Instant.ofEpochMilli(this)
//            .atZone(java.time.ZoneId.systemDefault())
//            .toLocalDate()
//    }
}
