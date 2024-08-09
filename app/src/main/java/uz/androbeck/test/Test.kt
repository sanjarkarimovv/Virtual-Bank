package uz.androbeck.test

import java.io.Serializable
import uz.androbeck.virtualbank.R

data class Model(
    val logo: Int? = null,
    val title: String? = null,
    val phoneNumber: String? = null,
) : Serializable

data class PlacesPayment(
    val logo: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val distance: Int? = null,
)

object MyList {
    val savedPaymentList = listOf(
        Model(
            logo = R.drawable.ucell_logo,
            title = "Mening telefonim",
            phoneNumber = "+998944856603"
        ),
        Model(
            logo = R.drawable.ucell_logo,
            title = "Dadamning telefoni",
            phoneNumber = "+998500721763"
        ),
        Model(
            logo = R.drawable.beeline_logo,
            title = "Onamning telefoni",
            phoneNumber = "+998900722266"
        ),
        Model(logo = R.drawable.gas_logo, title = "GAZ"),
        Model(title = ADD_HOME)
    )
    val servesPaymentList = listOf(
        Model(logo = R.drawable.ic_language, title = "Internet Provider"),
        Model(logo = R.drawable.ic_phone, title = "Mobil operators"),
        Model(logo = R.drawable.ic_permission, title = "Davlat xizmatlari"),
        Model(logo = R.drawable.ic_support, title = "Call centr xizmatlari"),
        Model(logo = R.drawable.ic_security, title = "Xavfsizlik xizmati"),
    )

    val myHomeList = listOf(
        Model(title = "Olmazor ko'cha 291 - uy"),
        Model(title = "Yunusobod 47/33 xonadon"),
        Model(title = "Besh ariq ko'cha 137 - uy"),
        Model(title = ADD_HOME),
    )
    val placesList = listOf(
        PlacesPayment(
            logo = R.drawable.odil_logo,
            title = "ODIL SCHOOL QORAKO'L - Yunusobod",
            description = "Yunusobod tumani, Bodomzor mahallasi, mo'ljal 'Bodomzor masjid'",
            distance = 350
        ),
        PlacesPayment(
            logo = R.drawable.beeline_logo,
            title = "BEELINE COMPANY - Yashnabod",
            description = "Yashnabod tumani, Ko'kcha mahallasi, mo'ljal 'Adamarin kiyim do'koni'",
            distance = 730
        ),
        PlacesPayment(
            logo = R.drawable.ucell_logo,
            title = "UCELL COMPANY - Chilonzor",
            description = "Chilonzor tumani, Olmazor mahallasi, mo'ljal 'Farhod bozori'",
            distance = 730
        ),
        PlacesPayment(
            logo = R.drawable.odil_logo,
            title = "ODIL SCHOOL QORAKO'L - Yunusobod",
            description = "Yunusobod tumani, Bodomzor mahallasi, mo'ljal 'Bodomzor masjid'",
            distance = 350
        ),
        PlacesPayment(
            logo = R.drawable.beeline_logo,
            title = "BEELINE COMPANY - Yashnabod",
            description = "Yashnabod tumani, Ko'kcha mahallasi, mo'ljal 'Adamarin kiyim do'koni'",
            distance = 730
        ),
        PlacesPayment(
            logo = R.drawable.ucell_logo,
            title = "UCELL COMPANY - Chilonzor",
            description = "Chilonzor tumani, Olmazor mahallasi, mo'ljal 'Farhod bozori'",
            distance = 730
        ),
    )
}

const val ADD_HOME = "Uy qo'shish"