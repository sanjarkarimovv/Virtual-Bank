package uz.androbeck.virtualbank.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.androbeck.virtualbank.data.db.dao.HomeDao
import uz.androbeck.virtualbank.data.db.dao.UserDao
import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import uz.androbeck.virtualbank.data.db.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Database(entities = [UserEntity::class, HomeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun homeDao(): HomeDao
}