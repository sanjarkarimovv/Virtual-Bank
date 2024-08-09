package uz.androbeck.virtualbank.domain.useCases.history

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.domain.mapper.history.LastTransfersMapper
import javax.inject.Inject

class LastTransfersUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val lastTransfersMapper: LastTransfersMapper
) {
    operator fun invoke() =
        historyRepository.getLastTransfers().map {
            lastTransfersMapper.toUIModel(it)
        }

}