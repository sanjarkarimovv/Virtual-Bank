package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.source.remote.history.HistoryPagingSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyRemoteDatasource: HistoryRemoteDatasource
) : HistoryRepository {

    override fun getLastTransfers() = historyRemoteDatasource.getLastTransfers()

    override fun getHistory(): Flow<PagingData<InComeAndOutComeResDto>> = Pager(
        config = PagingConfig(
            pageSize = 6,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { HistoryPagingSource(historyRemoteDatasource) }
    ).flow
}