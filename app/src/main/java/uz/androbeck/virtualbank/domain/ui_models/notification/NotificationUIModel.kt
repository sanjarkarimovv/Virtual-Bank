package uz.androbeck.virtualbank.domain.ui_models.notification

data class NotificationUIModel(
    override val time: Long,
    val image: String? = null,
    val title: String? = null,
    val description: String? = null,
    val navigationType: Int? = null,
) : TimeBasedItem {
    constructor() : this(0, null, null, null)
}
//Navigation Type -> 1-Update, 2-Interest free transfer, 3-Payment