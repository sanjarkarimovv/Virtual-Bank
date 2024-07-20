package uz.androbeck.virtualbank.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.androbeck.virtualbank.model.entity.UserEntity


@Dao
interface UserDao {
    @Query("SELECT* FROM userentity")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM userentity WHERE id = :id")
    fun getUserById(id: Int): UserEntity

    @Insert
    fun insertUser(userModel: UserEntity)

    @Update(entity = UserEntity::class)
    fun updateUser(userModel: UserEntity)

    @Query("UPDATE userentity SET name = password WHERE id = :id")
    fun updateUserNameById(id: Int, newName: String)

    @Query("UPDATE UserEntity SET password = :newPassword WHERE id = :id")
    fun updateUserNumber(id: Int, newPassword: String)

    @Delete
    fun deleteUser(userModel: UserEntity)
}