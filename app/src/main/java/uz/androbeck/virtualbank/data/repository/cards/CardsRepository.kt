package uz.androbeck.virtualbank.data.repository.cards

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.cards.GetCardsResDto

interface CardsRepository {
    suspend fun getCards(): Flow<GetCardsResDto>
}