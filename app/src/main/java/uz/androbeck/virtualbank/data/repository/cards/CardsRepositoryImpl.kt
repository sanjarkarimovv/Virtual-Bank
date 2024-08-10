package uz.androbeck.virtualbank.data.repository.cards

import uz.androbeck.virtualbank.data.source.remote.cards.CardsDataSourceImpl

class CardsRepositoryImpl(
    private val dataSource: CardsDataSourceImpl
) : CardsRepository {
    override suspend fun getCards() = dataSource.getCards()
}