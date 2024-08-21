package uz.androbeck.virtualbank.domain.useCases.history

import androidx.paging.PagingData
import androidx.paging.flatMap
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.domain.mapper.history.GetHistoryMapper
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.utils.extentions.formatToDayMonthYear
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val getHistoryMapper: GetHistoryMapper,
) {
    operator fun invoke(): Flow<PagingData<HistoryItem>> {
        return historyRepository.getHistory().map { pagingData ->
            var lastHeaderDate: Long? = null

            pagingData.map { historyDto ->
                val uiModel = getHistoryMapper.toUIModel(historyDto)
                val currentDate = uiModel.time.formatToDayMonthYear()

                val items = mutableListOf<HistoryItem>()

                if (lastHeaderDate == null || currentDate.toLong() != lastHeaderDate) {
                    items.add(HistoryItem.Header(uiModel.time))
                    lastHeaderDate = currentDate.toLong()
                }

                items.add(HistoryItem.Content(uiModel))

                items
            }.flatMap { it }
        }
    }
}