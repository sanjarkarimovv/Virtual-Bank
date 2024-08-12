package uz.androbeck.virtualbank.domain.ui_models.home

import uz.androbeck.virtualbank.domain.ui_models.common.InComeAndOutComeUIModel

data class LastTransferUIModel(
    val transferUIModel: List<InComeAndOutComeUIModel>? = null,
)
