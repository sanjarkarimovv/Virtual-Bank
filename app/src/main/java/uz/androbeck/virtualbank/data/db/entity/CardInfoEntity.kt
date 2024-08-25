package uz.androbeck.virtualbank.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info_entity")
data class CardInfoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "theme_type")
    val themeType: Int=0,
    @ColumnInfo("pan")
    val pan: String?=null
)