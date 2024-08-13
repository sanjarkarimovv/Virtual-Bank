package uz.androbeck.virtualbank.data.source.remote.history

import uz.androbeck.virtualbank.data.api.HistoryService
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyService: HistoryService
) : HistoryRemoteDataSource {
    override suspend  fun getHistory(size: Int, currentPage: Int) = historyService.getHistory(
        size = size,
        currentPage = currentPage
    )
}