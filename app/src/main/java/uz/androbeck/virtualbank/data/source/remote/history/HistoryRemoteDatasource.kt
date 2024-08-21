package uz.androbeck.virtualbank.data.source.remote.history

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto

interface HistoryRemoteDatasource {

    fun getLastTransfers(): Flow<LastTransfersResDto>

    suspend fun getHistory(size: Int, currentPage: Int): GetHistoryResDto

}