package uz.androbeck.virtualbank.data.source.remote.history

import android.annotation.SuppressLint
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import javax.inject.Inject

class HistoryRemoteDataSourceImpl @Inject constructor(
    private val historyService: HistoryService
) : HistoryRemoteDatasource {

    override fun getLastTransfers() = flow {
        emit(historyService.getLastTransfers())
    }

//    override suspend fun getHistory(
//        size: Int,
//        currentPage: Int
//    ): List<InComeAndOutComeResDto> =
//        historyService.getHistory(size, currentPage).transferResDto ?: emptyList()

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getHistory(size: Int, currentPage: Int): GetHistoryResDto {
        val response = historyService.getHistory(size, currentPage)
        println(":::API Response: ${response.transferResDto}")
        println(":::API Response: $response")

        return response

    }
}

