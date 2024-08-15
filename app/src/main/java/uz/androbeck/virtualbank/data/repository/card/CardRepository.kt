package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto

interface CardRepository {
    suspend fun deleteCard(): Flow<MessageResDto>

}