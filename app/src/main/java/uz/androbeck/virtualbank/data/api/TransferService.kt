package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto
import uz.androbeck.virtualbank.utils.Constants

interface TransferService {
    @POST(Constants.Endpoint.GET_FEE)
    suspend fun getFee(
        @Body request: GetFeeReqDto
    ): GetFeeResDto
}