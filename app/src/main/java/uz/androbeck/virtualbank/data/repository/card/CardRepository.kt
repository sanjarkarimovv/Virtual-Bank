package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.card.GetCardsResDto

interface CardRepository {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun getCards(): Flow<GetCardsResDto>

}