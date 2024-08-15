package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.utils.Constants

interface CardService {

    @DELETE(Constants.Endpoint.DELETE_CARD)
    suspend fun deleteCard(): MessageResDto

    @POST(Constants.Endpoint.ADD_CARD)
    suspend fun addCard(
        @Body addCardReqDto: AddCardReqDto
    ): MessageResDto
}

// Service->Source->Repository->UseCase->~~ViewModel->Fragment/Activity