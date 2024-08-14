package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto
import uz.androbeck.virtualbank.utils.Constants

interface CardsService {
    @GET(Constants.Endpoint.GET_CARDS)
    suspend fun getCards(): CardsResDto
}