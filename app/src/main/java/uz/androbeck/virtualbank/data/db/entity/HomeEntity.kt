package uz.androbeck.virtualbank.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.androbeck.virtualbank.ui.screens.HomeComponents

@Entity(tableName = "home_entity")
data class HomeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "component")
    var name: HomeComponents = HomeComponents.Cards,
    @ColumnInfo(name = "is_show")
    var isShow: Boolean = true,
    @ColumnInfo(name = "value")
    var value: String? = null
    ){
   // constructor(): this(0, HomeComponents.Cards, true, null)
}