package uz.androbeck.virtualbank.ui.screens.widgetSettings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.domain.useCases.home.GetComponentsFromCacheUseCase
import uz.androbeck.virtualbank.domain.useCases.home.UpdateComponentsInCatchUseCase
import javax.inject.Inject

@HiltViewModel
class WidgetSettingsViewModel @Inject constructor(
    private val getComponentsFromCacheUseCase: GetComponentsFromCacheUseCase,
    private val updateComponentsInCatchUseCase: UpdateComponentsInCatchUseCase
) : ViewModel() {
    private val _uiEvent = MutableLiveData<WidgetSettingsUIEvent>()
    val uiEvent: LiveData<WidgetSettingsUIEvent> = _uiEvent

    fun getUIComponents() {
        val list0 = mutableListOf<UiComponents>()
        val list1 = mutableListOf<UiComponents>()
        viewModelScope.launch {
            getComponentsFromCacheUseCase.invoke().onEach { list ->
                list1.clear()
                list0.clear()
                list.forEach { i ->
                    if (i.isShow) {
                        list0.add(i)
                    } else {
                        list1.add(i)
                    }
                }
                _uiEvent.value = (WidgetSettingsUIEvent.Show(list0.toList()))
                _uiEvent.value = (WidgetSettingsUIEvent.NotShow(list1.toList()))
            }.launchIn(this)

        }
    }

    fun put(uiComponents: UiComponents) {
        viewModelScope.launch(Dispatchers.IO) {
            updateComponentsInCatchUseCase.updateComponents(uiComponents.id, uiComponents.isShow)
        }
    }

}