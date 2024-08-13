package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.data.dto.response.home.LastTransfersResDto

interface HistoryRepository {
    fun getHistory(): Pager<Int, GetHistoryResDto>
}