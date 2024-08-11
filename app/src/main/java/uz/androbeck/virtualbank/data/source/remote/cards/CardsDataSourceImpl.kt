package uz.androbeck.virtualbank.data.source.remote.cards

import kotlinx.coroutines.flow.flow

class CardsDataSourceImpl(
    private val service: CardsService
) : CardsDataSource {
    override suspend fun getCards() = flow { emit(service.getCards()) }
}