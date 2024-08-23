package uz.androbeck.virtualbank.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import java.net.IDN

@Dao
interface HomeDao {

    @Query("SELECT* FROM home_entity")
    fun getAllComponents(): Flow<List<HomeEntity>>

    @Insert
    suspend fun addComponent(homeEntity: HomeEntity)

//    @Query("SELECT * FROM home_entity WHERE id =:id")
//    suspend fun deleteItemBy(id: Int): Int

    @Query("UPDATE home_entity SET `is_show` =:isVisible WHERE id = :id")
    suspend fun updateComponent(id:Int,isVisible:Boolean)
}