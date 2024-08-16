package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.dto.response.card.GetCardsResDto
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
) : CardRepository {
    override fun deleteCard() = cardRemoteDataSource.deleteCard()
    override fun addCard(addCardReqDto: AddCardReqDto) =
        cardRemoteDataSource.addCard(addCardReqDto)
    override suspend fun getCards(): Flow<GetCardsResDto> = cardRemoteDataSource.getCards()
}