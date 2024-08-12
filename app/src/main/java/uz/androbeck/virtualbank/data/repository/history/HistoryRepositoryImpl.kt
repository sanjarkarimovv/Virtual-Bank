package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import uz.androbeck.virtualbank.data.dto.response.home.LastTransfersResDto
import uz.androbeck.virtualbank.data.pager.HistoryPagingSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyPagingSource: HistoryPagingSource
) : HistoryRepository {
    override fun getLastTransfers(): Pager<Int, LastTransfersResDto> {
        return Pager(
            PagingConfig(
                pageSize = 10
            )
        )
        { historyPagingSource}

    }

}