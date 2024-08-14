package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.GetCardsResDto
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
):CardRepository {
    override suspend fun deleteCard()=cardRemoteDataSource.deleteCard()
    override suspend fun getCards()=cardRemoteDataSource.getCards()
    }
