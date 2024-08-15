package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto

interface CardRemoteDataSource {
    fun deleteCard(): Flow<MessageResDto>
    fun addCard(addCardReqDto: AddCardReqDto): Flow<MessageResDto>

}