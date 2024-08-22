package uz.androbeck.virtualbank.domain.ui_models.transfer

import kotlinx.serialization.SerialName

data class TransferUIModel(
    val type  : String? = null,
    val sender_id : String? = null,
    val receiver_id : String? = null,
    val amount : Long? = null
)
