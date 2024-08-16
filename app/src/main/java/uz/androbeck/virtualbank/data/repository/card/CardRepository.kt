package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto

interface CardRepository {
    fun deleteCard(): Flow<MessageResDto>
    fun addCard(addCardReqDto: AddCardReqDto): Flow<MessageResDto>
    fun getCards(): Flow<List<CardResDto>>
}