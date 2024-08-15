package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto

interface CardRemoteDataSource {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun putUpdateCard(request: UpdateCardReqDto): Flow<MessageResDto>
}