package uz.androbeck.virtualbank.domain.ui_models.cards

import android.os.Parcel
import android.os.Parcelable

data class CardUIModel(
    val id: Int? = 0,
    val amount: Double? = 0.0,
    val name: String? = null,
    val owner: String? = null,
    var pan: String? = null,
    val expiredYear: Int? = 0,
    val expiredMonth: Int? = 0,
    val themeType: Int? = 0,
    val isVisible: Boolean? = false
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(amount)
        parcel.writeString(name)
        parcel.writeString(owner)
        parcel.writeString(pan)
        parcel.writeValue(expiredYear)
        parcel.writeValue(expiredMonth)
        parcel.writeValue(themeType)
        parcel.writeValue(isVisible)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardUIModel> {
        override fun createFromParcel(parcel: Parcel): CardUIModel {
            return CardUIModel(parcel)
        }

        override fun newArray(size: Int): Array<CardUIModel?> {
            return arrayOfNulls(size)
        }
    }
}
