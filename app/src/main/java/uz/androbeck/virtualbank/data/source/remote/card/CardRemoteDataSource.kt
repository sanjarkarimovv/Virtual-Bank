package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.card.GetCardsResDto

interface CardRemoteDataSource {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun getCards(): Flow<GetCardsResDto>
}