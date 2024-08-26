package uz.androbeck.virtualbank.data.repository.card

import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.source.local.CardsLocalDataSource
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource,
    private val cardsLocalDataSource: CardsLocalDataSource
) : CardRepository {

    override fun deleteCard(id:String) = cardRemoteDataSource.deleteCard(id)
    override fun deleteCard() = cardRemoteDataSource.deleteCard()

    override fun addCard(addCardReqDto: AddCardReqDto) =
        cardRemoteDataSource.addCard(addCardReqDto)

    override fun getCards() = cardRemoteDataSource.getCards()

    override suspend fun getCardsFromCache(): List<CardInfoEntity> {
        return cardsLocalDataSource.getCards()
    }


}