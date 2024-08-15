package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto
import uz.androbeck.virtualbank.utils.Constants

interface CardService {

    @DELETE(Constants.Endpoint.DELETE_CARD)
    suspend fun deleteCard(): MessageResDto

    @POST(Constants.Endpoint.UPDATE_CARD)
    suspend fun putUpdateCard(
        @Body request: UpdateCardReqDto
    ): MessageResDto
}