package uz.androbeck.virtualbank.data.repository.history

import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyRemoteDatasource: HistoryRemoteDatasource,
) : HistoryRepository {
    override fun getLastTransfers() = historyRemoteDatasource.getLastTransfers()
}