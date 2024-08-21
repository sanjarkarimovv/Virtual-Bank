package uz.androbeck.virtualbank.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.androbeck.virtualbank.data.db.dao.CardInfoDao
import uz.androbeck.virtualbank.data.db.dao.UserDao
import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity
import uz.androbeck.virtualbank.data.db.entity.UserEntity
import javax.inject.Inject

@Database(entities = [UserEntity::class,CardInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        private var INSTANCE:AppDatabase?=null
        @Synchronized
        fun getInstance(context: Context):AppDatabase{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context
                    .applicationContext,AppDatabase::class
                    .java,"user.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }

    }
    abstract fun userDao(): UserDao
    abstract fun cardInfoDao():CardInfoDao
}