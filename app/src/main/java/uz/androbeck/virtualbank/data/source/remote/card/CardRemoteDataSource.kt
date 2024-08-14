package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.GetCardsResDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto

interface CardRemoteDataSource {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun getCards(): Flow<List<GetCardsResDto>>
}