package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto

interface CardRepository {
    suspend fun deleteCard(): Flow<MessageResDto>
    suspend fun putUpdateCard(request: UpdateCardReqDto): Flow<MessageResDto>
}