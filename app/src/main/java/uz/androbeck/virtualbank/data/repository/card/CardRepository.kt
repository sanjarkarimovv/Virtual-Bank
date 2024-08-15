package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto

interface CardRepository {
    fun putUpdateCard(request: UpdateCardReqDto): Flow<MessageResDto>
    fun deleteCard(): Flow<MessageResDto>
    fun addCard(addCardReqDto: AddCardReqDto): Flow<MessageResDto>


}