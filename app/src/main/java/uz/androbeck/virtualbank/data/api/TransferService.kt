package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetCardOwnerByPanReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TransferRequestDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetCardOwnerByPanResDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto
import uz.androbeck.virtualbank.utils.Constants

interface TransferService {
    @POST(Constants.Endpoint.GET_FEE)
    suspend fun getFee(
        @Body request: GetFeeReqDto
    ): GetFeeResDto
    @POST(Constants.Endpoint.TRANSFER)
    suspend fun transfer(
        @Body request: TransferRequestDto
    ) :TokenResDto
    @POST(Constants.Endpoint.Get_CARD_OWNER_BY_PAN)
    suspend fun getCardOwner(
        @Body request: GetCardOwnerByPanReqDto
    ):GetCardOwnerByPanResDto
}