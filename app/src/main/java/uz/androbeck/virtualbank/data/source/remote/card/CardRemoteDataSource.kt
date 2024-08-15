package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto

interface CardRemoteDataSource {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun addCard(addCardReqDto: AddCardReqDto): Flow<MessageResDto>

}