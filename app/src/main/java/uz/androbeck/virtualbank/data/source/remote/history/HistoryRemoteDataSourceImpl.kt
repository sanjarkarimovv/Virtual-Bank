package uz.androbeck.virtualbank.data.source.remote.history

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyService: HistoryService
) : HistoryRemoteDatasource {

    override fun getLastTransfers() = flow {
        emit(historyService.getLastTransfers())
    }

    override  fun getHistory(
        size: Int,
        currentPage: Int
    ): Flow<List<InComeAndOutComeResDto>> {
        return flow {
            emit(historyService.getHistory(size,currentPage).transferResDto.orEmpty())
        }

    }

}