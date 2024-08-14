package uz.androbeck.virtualbank.data.source.remote.card

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.CardService
import javax.inject.Inject

class CardRemoteDataSourceImpl @Inject constructor(
    private val cardService: CardService
):CardRemoteDataSource {
    override suspend fun deleteCard()= flow {
        emit(cardService.deleteCard())

    }

    override suspend fun getCards()=flow{
    emit(cardService.getCards())

    }

}