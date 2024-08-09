package uz.androbeck.virtualbank.data.source.remote.history

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HistoryService
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyService: HistoryService
): HistoryRemoteDatasource
{
    override fun getLastTransfers()= flow {
        emit(historyService.getLastTransfers())
    }
}