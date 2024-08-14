package uz.androbeck.virtualbank.data.repository.card

import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
):CardRepository {
    override suspend fun deleteCard()=cardRemoteDataSource.deleteCard()
    override suspend fun getCards()=cardRemoteDataSource.getCards()
    }
