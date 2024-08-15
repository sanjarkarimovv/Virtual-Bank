package uz.androbeck.virtualbank.domain.mock_data

import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.payments.PaymentUIModel
import uz.androbeck.virtualbank.domain.ui_models.payments.PlacesPaymentUIModel
import uz.androbeck.virtualbank.domain.ui_models.payments.SavedPaymentUIModel
import uz.androbeck.virtualbank.utils.Constants.String.ADD_HOME

object AppHardcodeData {
    val savedPaymentList = listOf(
        SavedPaymentUIModel(
            logo = R.drawable.ucell_logo,
            title = "Mening telefonim",
            phoneNumber = "+998944856603"
        ),
        SavedPaymentUIModel(
            logo = R.drawable.ucell_logo,
            title = "Dadamning telefoni",
            phoneNumber = "+998500721763"
        ),
        SavedPaymentUIModel(
            logo = R.drawable.beeline_logo,
            title = "Onamning telefoni",
            phoneNumber = "+998900722266"
        ),
        SavedPaymentUIModel(logo = R.drawable.gas_logo, title = "GAZ"),
        SavedPaymentUIModel(title = ADD_HOME)
    )
    val servicePaymentList = listOf(
        PaymentUIModel(logo = R.drawable.ic_language, title = "Internet Provider"),
        PaymentUIModel(logo = R.drawable.ic_phone, title = "Mobil operators"),
        PaymentUIModel(logo = R.drawable.ic_permission, title = "Davlat xizmatlari"),
        PaymentUIModel(logo = R.drawable.ic_support, title = "Call centr xizmatlari"),
        PaymentUIModel(logo = R.drawable.ic_security, title = "Xavfsizlik xizmati"),
    )
    val myHomePaymentList = listOf(
        PaymentUIModel(logo = R.drawable.ic_home, title = "Olmazor ko'cha 291 - uy"),
        PaymentUIModel(logo = R.drawable.ic_home, title = "Yunusobod 47/33 xonadon"),
        PaymentUIModel(logo = R.drawable.ic_home, title = "Besh ariq ko'cha 137 - uy"),
        PaymentUIModel(logo = R.drawable.ic_home, title = ADD_HOME),
    )
    val placesPaymentList = listOf(
        PlacesPaymentUIModel(
            logo = R.drawable.odil_logo,
            title = "ODIL SCHOOL QORAKO'L - Yunusobod",
            description = "Yunusobod tumani, Bodomzor mahallasi, mo'ljal 'Bodomzor masjid'",
            distance = 350
        ),
        PlacesPaymentUIModel(
            logo = R.drawable.beeline_logo,
            title = "BEELINE COMPANY - Yashnabod",
            description = "Yashnabod tumani, Ko'kcha mahallasi, mo'ljal 'Adamarin kiyim do'koni'",
            distance = 730
        ),
        PlacesPaymentUIModel(
            logo = R.drawable.ucell_logo,
            title = "UCELL COMPANY - Chilonzor",
            description = "Chilonzor tumani, Olmazor mahallasi, mo'ljal 'Farhod bozori'",
            distance = 730
        ),
        PlacesPaymentUIModel(
            logo = R.drawable.odil_logo,
            title = "ODIL SCHOOL QORAKO'L - Yunusobod",
            description = "Yunusobod tumani, Bodomzor mahallasi, mo'ljal 'Bodomzor masjid'",
            distance = 350
        ),
        PlacesPaymentUIModel(
            logo = R.drawable.beeline_logo,
            title = "BEELINE COMPANY - Yashnabod",
            description = "Yashnabod tumani, Ko'kcha mahallasi, mo'ljal 'Adamarin kiyim do'koni'",
            distance = 730
        ),
        PlacesPaymentUIModel(
            logo = R.drawable.ucell_logo,
            title = "UCELL COMPANY - Chilonzor",
            description = "Chilonzor tumani, Olmazor mahallasi, mo'ljal 'Farhod bozori'",
            distance = 730
        ),
    )
    val cardStyleImages = listOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5,
        R.drawable.photo_6,
        R.drawable.photo_7,
        R.drawable.photo_8,
        R.drawable.photo_9,
        R.drawable.photo_10,
        R.drawable.photo_11,
        R.drawable.photo_12,
    )
}