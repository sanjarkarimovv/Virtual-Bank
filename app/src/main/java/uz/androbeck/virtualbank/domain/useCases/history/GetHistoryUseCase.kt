package uz.androbeck.virtualbank.domain.useCases.history

import androidx.paging.map
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.domain.mapper.history.HistoryMapper
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val historyMapper: HistoryMapper,
    ) {
    operator fun invoke() =
        historyRepository.getHistory().flow.map {pagingData->
            pagingData.map { historyDto->
            historyMapper.toUIModel(historyDto)
        }
        }
}