package uz.androbeck.virtualbank.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.androbeck.virtualbank.data.db.dao.UserDao
import uz.androbeck.virtualbank.data.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}