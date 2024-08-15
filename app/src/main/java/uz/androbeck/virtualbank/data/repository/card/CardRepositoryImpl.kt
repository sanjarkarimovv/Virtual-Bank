package uz.androbeck.virtualbank.data.repository.card

import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
) : CardRepository {

    override fun deleteCard() = cardRemoteDataSource.deleteCard()
    override fun putUpdateCard(request: UpdateCardReqDto) =
        cardRemoteDataSource.putUpdateCard(request)

    override fun addCard(addCardReqDto: AddCardReqDto) =
        cardRemoteDataSource.addCard(addCardReqDto)

}