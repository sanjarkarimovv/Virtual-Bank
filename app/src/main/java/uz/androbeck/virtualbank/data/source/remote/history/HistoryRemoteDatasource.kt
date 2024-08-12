package uz.androbeck.virtualbank.data.source.remote.history

import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.home.LastTransfersResDto

interface HistoryRemoteDataSource {

    fun getLastTransfers(): Pager<Int, LastTransfersResDto>
}