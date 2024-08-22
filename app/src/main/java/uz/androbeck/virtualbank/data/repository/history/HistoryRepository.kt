package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto

interface HistoryRepository {
    fun getLastTransfers(): Flow<List<InComeAndOutComeResDto>>
    fun getHistory(): Pager<Int,GetHistoryResDto>
}