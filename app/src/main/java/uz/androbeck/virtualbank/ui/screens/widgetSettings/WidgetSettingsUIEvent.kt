package uz.androbeck.virtualbank.ui.screens.widgetSettings

import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents

sealed class WidgetSettingsUIEvent {
    data class Show(
        val list: List<UiComponents>
    ) : WidgetSettingsUIEvent()

    data class NotShow(
        val list: List<UiComponents>
    ) : WidgetSettingsUIEvent()
}