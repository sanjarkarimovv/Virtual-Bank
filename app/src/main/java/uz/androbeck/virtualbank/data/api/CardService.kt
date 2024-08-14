package uz.androbeck.virtualbank.data.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.utils.Constants

interface CardService {

    @DELETE(Constants.Endpoint.DELETE_CARD)
    suspend fun deleteCard(): MessageResDto

    @GET(Constants.Endpoint.GET_CARD)
    suspend fun getCards(): List<CardResDto>
}