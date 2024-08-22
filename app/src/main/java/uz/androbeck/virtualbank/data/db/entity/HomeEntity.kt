package uz.androbeck.virtualbank.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.androbeck.virtualbank.ui.screens.HomeComponents

@Entity(tableName = "home_entity")
data class HomeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "component")
    val name: HomeComponents,
    @ColumnInfo(name = "is show")
    val isShow: Boolean,
    @ColumnInfo(name = "value")
    var value: String? = null
    ){
   // constructor(): this(0, HomeComponents.Cards, true, null)
}