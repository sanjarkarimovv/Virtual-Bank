package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.utils.Constants

interface CardService {

    @DELETE(Constants.Endpoint.DELETE_CARD + "{id}")
    suspend fun deleteCard(
        @Path("id") id: String
    ): MessageResDto

    @GET(Constants.Endpoint.GET_CARD)
    suspend fun getCards(): List<CardResDto>

    @POST(Constants.Endpoint.ADD_CARD)
    suspend fun addCard(
        @Body addCardReqDto: AddCardReqDto
    ): MessageResDto
}