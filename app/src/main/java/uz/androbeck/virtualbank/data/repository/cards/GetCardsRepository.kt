package uz.androbeck.virtualbank.data.repository.cards

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto

interface GetCardsRepository  {
    fun getCards(): Flow<CardsResDto>
}