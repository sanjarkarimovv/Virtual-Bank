package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents

sealed class HomeComponentsUiEvent {
    data class ComponentForUi(val list: List<UiComponents>):HomeComponentsUiEvent()

}