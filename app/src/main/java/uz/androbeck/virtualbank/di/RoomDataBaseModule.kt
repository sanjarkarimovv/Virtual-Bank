package uz.androbeck.virtualbank.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.local.dao.UserDao
import uz.androbeck.virtualbank.data.local.entity.UserEntity
import javax.inject.Singleton

const val DATABASE_NAME = "virtual_bank_database"

@Module
@InstallIn(SingletonComponent::class)
object RoomDataBaseModule {

    @Database(entities = [UserEntity::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            //.addMigrations() migratsiya qoshamizmi?
            .build()
    }

    @Provides
    fun provideUserDao(roomDatabase: AppDatabase): UserDao {
        return roomDatabase.userDao()
    }

}