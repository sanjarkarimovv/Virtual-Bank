package uz.androbeck.virtualbank.data.source.remote.cards

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.cards.GetCardsResDto

interface CardsDataSource {
    suspend fun getCards(): Flow<GetCardsResDto>
}