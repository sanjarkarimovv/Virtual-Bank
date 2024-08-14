package uz.androbeck.virtualbank.data.source.remote.cards

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.CardsService
import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto
import javax.inject.Inject

class CardsRemoteDataSourceImpl @Inject constructor(
    private val cardsService: CardsService
): CardsRemoteDatasource {
    override fun getCards()= flow {
        emit(cardsService.getCards())

    }

}