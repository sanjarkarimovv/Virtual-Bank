package uz.androbeck.virtualbank.domain.ui_models.transfer

data class GetFeeReqUIModel(
    val senderId:String?=null,
    val receiver:String?=null,
    val amount: String? =null,
) {
}