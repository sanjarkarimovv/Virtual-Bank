package uz.androbeck.virtualbank.data.source.remote.history

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto

interface HistoryRemoteDatasource {
    fun getLastTransfers(): Flow<LastTransfersResDto>

}