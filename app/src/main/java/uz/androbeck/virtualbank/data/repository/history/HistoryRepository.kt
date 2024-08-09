package uz.androbeck.virtualbank.data.repository.history

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto

interface HistoryRepository {
    fun getLastTransfers(): Flow<LastTransfersResDto>
}