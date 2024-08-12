package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.home.LastTransfersResDto

interface HistoryRepository {
    fun getLastTransfers(): Pager<Int, LastTransfersResDto>


}