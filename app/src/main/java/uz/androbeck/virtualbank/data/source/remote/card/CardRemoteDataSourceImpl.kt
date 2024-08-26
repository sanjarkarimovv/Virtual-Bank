package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.CardService
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.request.card.DeleteCardReqDto
import javax.inject.Inject

class CardRemoteDataSourceImpl @Inject constructor(
    private val cardService: CardService
) : CardRemoteDataSource {
    override fun deleteCard(id:String) = flow {
        emit(cardService.deleteCard(id))
    }

    override fun addCard(addCardReqDto: AddCardReqDto) = flow {
        emit(cardService.addCard(addCardReqDto))
    }

    override fun getCards() = flow {
        emit(cardService.getCards())
    }
}