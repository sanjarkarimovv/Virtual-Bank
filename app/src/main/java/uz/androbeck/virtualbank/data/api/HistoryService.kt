package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto
import uz.androbeck.virtualbank.utils.Constants

interface HistoryService {

    @GET(Constants.Endpoint.LAST_TRANSFERS)
    suspend fun getLastTransfers(): LastTransfersResDto
}