package uz.androbeck.virtualbank.data.source.remote.cards

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto

interface CardsRemoteDatasource {
    fun getCards(): Flow<CardsResDto>
}