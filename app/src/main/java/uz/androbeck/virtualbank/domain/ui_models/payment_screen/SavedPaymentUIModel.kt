package uz.androbeck.virtualbank.domain.ui_models.payment_screen
import java.io.Serializable
data class SavedPaymentUIModel(
    val logo: Int? = null,
    val title: String? = null,
    val phoneNumber: String? = null,
) : Serializable