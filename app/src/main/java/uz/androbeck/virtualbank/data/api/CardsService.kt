package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.response.cards.GetCardsResDto

interface CardsService {
    @GET
    suspend fun getCards(): GetCardsResDto
}