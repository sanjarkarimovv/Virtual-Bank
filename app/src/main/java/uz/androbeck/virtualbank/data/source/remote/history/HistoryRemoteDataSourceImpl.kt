package uz.androbeck.virtualbank.data.source.remote.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import uz.androbeck.virtualbank.data.dto.response.home.LastTransfersResDto
import uz.androbeck.virtualbank.data.pager.HistoryPagingSource
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyPagingSource: HistoryPagingSource
) : HistoryRemoteDataSource {
    override fun getLastTransfers(): Pager<Int, LastTransfersResDto> {
        return Pager(
            PagingConfig(
                pageSize = 10
            )
        ){ historyPagingSource }
    }


}