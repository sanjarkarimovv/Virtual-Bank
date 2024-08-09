package uz.androbeck.virtualbank.domain.ui_models.home

import androidx.lifecycle.LiveData
import uz.androbeck.virtualbank.ui.screens.HomeComponents

data class UiComponents(
    val id: Int = 0,
    val name: HomeComponents,
    val isShow: Boolean,
    val value: String? = null
)