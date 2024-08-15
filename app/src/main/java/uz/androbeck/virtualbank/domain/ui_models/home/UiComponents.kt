package uz.androbeck.virtualbank.domain.ui_models.home

import uz.androbeck.virtualbank.ui.screens.HomeComponents

data class UiComponents(
    val id: Int = 0,
    val name: HomeComponents,
    var isShow: Boolean,
    val value: String?=null
)