package uz.androbeck.virtualbank.data.source.local

import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity

interface CardsLocalDataSource {
    suspend fun getCards() : List<CardInfoEntity>
}