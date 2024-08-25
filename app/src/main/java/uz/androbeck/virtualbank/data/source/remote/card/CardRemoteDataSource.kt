package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.request.card.DeleteCardReqDto
import uz.androbeck.virtualbank.data.dto.response.card.CardResDto

interface CardRemoteDataSource {
    fun getCards(): Flow<List<CardResDto>>
    fun deleteCard(id:String): Flow<MessageResDto>
    fun addCard(addCardReqDto: AddCardReqDto): Flow<MessageResDto>
}