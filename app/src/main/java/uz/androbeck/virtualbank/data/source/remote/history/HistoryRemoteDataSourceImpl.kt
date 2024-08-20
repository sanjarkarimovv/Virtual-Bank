package uz.androbeck.virtualbank.data.source.remote.history

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyService: HistoryService
) : HistoryRemoteDatasource {

    override fun getLastTransfers() = flow {
        emit(historyService.getLastTransfers())
    }

    override suspend fun getHistory(
        size: Int,
        currentPage: Int
    ): List<InComeAndOutComeResDto> =
        historyService.getHistory(size, currentPage).transferResDto ?: emptyList()
}