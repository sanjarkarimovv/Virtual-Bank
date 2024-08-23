package uz.androbeck.virtualbank.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity

@Dao
interface CardInfoDao {

    @Query("SELECT* FROM card_info_entity")
    suspend  fun getAllCard(): List<CardInfoEntity>

    @Query("SELECT * FROM card_info_entity WHERE id=:id")
    fun getCardById(id: Int): CardInfoEntity


    @Insert
    fun insertCard(cardInfoEntity: CardInfoEntity)

    @Update(entity = CardInfoEntity::class)
    fun updateCard(cardInfoEntity: CardInfoEntity)

    @Query("UPDATE card_info_entity SET pan = :newPan WHERE id = :id")
    fun updateCardPanById(id: Int, newPan:String)

    @Query("UPDATE card_info_entity SET theme_type = :newThemeType WHERE id = :id")
    fun updateUserNumber(id: Int, newThemeType: Int)

    @Delete
    fun deleteCardInfo(crdInfoEntity: CardInfoEntity)

}