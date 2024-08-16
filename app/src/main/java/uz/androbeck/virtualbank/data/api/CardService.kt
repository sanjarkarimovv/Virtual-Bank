package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.card.GetCardsResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.utils.Constants

interface CardService {

    @DELETE(Constants.Endpoint.DELETE_CARD)
    suspend fun deleteCard(): MessageResDto

    @GET(Constants.Endpoint.GET_CARD)
    suspend fun getCards(): GetCardsResDto

    @POST(Constants.Endpoint.ADD_CARD)
    suspend fun addCard(
        @Body addCardReqDto: AddCardReqDto
    ): MessageResDto
}