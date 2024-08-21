package uz.androbeck.virtualbank.data.source.local

import uz.androbeck.virtualbank.data.db.dao.CardInfoDao
import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity
import javax.inject.Inject

class CardsLocalDataSourceImpl @Inject constructor(
    private val cardsDao: CardInfoDao
) : CardsLocalDataSource {

    override suspend fun getCards(): List<CardInfoEntity> {
        return cardsDao.getAllCard()
    }
}