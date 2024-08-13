package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import uz.androbeck.virtualbank.data.source.remote.history.HistoryPagingSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyPagingSource: HistoryPagingSource,
    private val historyRemoteDatasource: HistoryRemoteDatasource
) : HistoryRepository {

    override fun getLastTransfers() = historyRemoteDatasource.getLastTransfers()

    override fun getHistory() = Pager(
        PagingConfig(
            pageSize = 6
        )
    )
    { historyPagingSource }
}