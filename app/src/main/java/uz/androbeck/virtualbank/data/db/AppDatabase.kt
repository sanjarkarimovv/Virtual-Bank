package uz.androbeck.virtualbank.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.androbeck.virtualbank.data.db.dao.CardInfoDao
import uz.androbeck.virtualbank.data.db.dao.HomeDao
import uz.androbeck.virtualbank.data.db.dao.UserDao
import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity
import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import uz.androbeck.virtualbank.data.db.entity.UserEntity

@Database(entities = [UserEntity::class,CardInfoEntity::class,HomeEntity::class], version = 2)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun homeDao(): HomeDao
    abstract fun cardInfoDao(): CardInfoDao
}