package uz.androbeck.virtualbank.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.utils.Constants

interface HistoryService {

    @GET(Constants.Endpoint.GET_HISTORY)
    suspend fun getHistory(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int,
    ): Response<List<GetHistoryResDto>>
}