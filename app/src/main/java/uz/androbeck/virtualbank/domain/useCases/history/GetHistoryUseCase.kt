package uz.androbeck.virtualbank.domain.useCases.history

import androidx.paging.map
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.domain.mapper.history.GetHistoryMapper
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val getHistoryMapper: GetHistoryMapper,
) {

    operator fun invoke() =
        historyRepository.getHistory().map {pagingData->
            pagingData.map { historyDto->
                getHistoryMapper.toUIModel(historyDto)
            }
        }
}