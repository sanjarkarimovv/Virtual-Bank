package uz.androbeck.virtualbank.data.repository.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import uz.androbeck.virtualbank.data.pager.HistoryPagingSource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyPagingSource: HistoryPagingSource
) : HistoryRepository {
    override fun getHistory() = Pager(
            PagingConfig(
                pageSize = 10
            )
        )
        { historyPagingSource }

}