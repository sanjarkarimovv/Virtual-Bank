package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.CardService
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import javax.inject.Inject

class CardRemoteDataSourceImpl @Inject constructor(
    private val cardService: CardService
):CardRemoteDataSource {
    override suspend fun deleteCard()= flow {
        emit(cardService.deleteCard())

    }
    override suspend fun addCard(addCardReqDto: AddCardReqDto)= flow {
        emit(cardService.addCard(addCardReqDto))
    }

}