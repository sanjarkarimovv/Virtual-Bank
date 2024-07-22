package uz.androbeck.virtualbank.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    // for example
    @ColumnInfo(name = "name")
    val nem: String? = null,
    @ColumnInfo(name = "password")
    val password: String? = null,
    @ColumnInfo(name = "token")
    val token: String? = null
)
