package uz.androbeck.virtualbank.data.source.remote.history

import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto

interface HistoryRemoteDataSource {
    suspend fun  getHistory(size: Int, currentPage: Int): List<GetHistoryResDto>
}