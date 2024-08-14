package uz.androbeck.virtualbank.data.repository.cards

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto
import uz.androbeck.virtualbank.data.source.remote.cards.CardsRemoteDatasource
import javax.inject.Inject

class GetCardsRepositoryImpl @Inject constructor(
    private val cardsRemoteDatasource: CardsRemoteDatasource
) : GetCardsRepository{
    override fun getCards()= cardsRemoteDatasource.getCards()

}