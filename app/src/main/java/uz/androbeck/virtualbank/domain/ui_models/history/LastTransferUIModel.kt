package uz.androbeck.virtualbank.domain.ui_models.history

data class LastTransferUIModel(
    val transferUIModel: List<TransferUIModel>? = null,
)

data class TransferUIModel(
    val type: String? = null,
    val from: String? = null,
    val to: String? = null,
    val amount: Double? = null,
    val time: Long? = null)
