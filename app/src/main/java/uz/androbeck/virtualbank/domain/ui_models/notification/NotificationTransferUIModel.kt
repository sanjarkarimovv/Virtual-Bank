package uz.androbeck.virtualbank.domain.ui_models.notification

import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel

data class NotificationTransferUIModel(
    var type: String? = null,
    var from: String? = null,
    var to: String? = null,
    var amount: Float = 0f,
    override var time: Long,
) : TimeBasedItem {
    fun toUIModel(it: NotificationTransferUIModel) {
        type = it.type
        from = it.from
        to = it.to
        amount = it.amount
        time = it.time
    }
}

fun InComeAndOutComeUIModel.toNotificationTransferUIModel(): NotificationTransferUIModel {
    return NotificationTransferUIModel(
        type = this.type,
        from = this.from,
        to = this.to,
        amount = this.amount,
        time = this.time
    )
}
