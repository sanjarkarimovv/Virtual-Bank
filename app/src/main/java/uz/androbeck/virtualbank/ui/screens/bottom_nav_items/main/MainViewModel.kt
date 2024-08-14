package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.home.CardModel
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.domain.ui_models.home.LastTransferModel
import uz.androbeck.virtualbank.domain.ui_models.home.PaymentsModel
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.domain.useCases.home.GetComponentsFromCacheUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutComponentsUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.UpdateComponentsInCatchUseCase
import uz.androbeck.virtualbank.ui.screens.HomeComponents
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val putUpdateInfoUseCase: PutUpdateInfoUseCase,
    private val getComponentsFromCacheUseCase: GetComponentsFromCacheUseCase,
    private val putComponentsUseCase: PutComponentsUseCase,
    private val updateComponentsInCatchUseCase: UpdateComponentsInCatchUseCase

) : ViewModel() {
    private val _homeComponents = MutableLiveData<HomeComponentsUiEvent>()
    val homeComponents: LiveData<HomeComponentsUiEvent> = _homeComponents
    private var components: List<UiComponents>? = null
    private val _uiData = MutableLiveData<HomeBodyModels>()
    val uiData: LiveData<HomeBodyModels> = _uiData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getComponentsFromCacheUseCase().onEach { it ->
                viewModelScope.launch {
                _homeComponents.value = (HomeComponentsUiEvent.ComponentForUi(it))
                components = it.ifEmpty {
                    // default components save
                    val list = listOf(
                        UiComponents(
                            name = HomeComponents.Cards,
                            isShow = true,
                            value = HomeComponents.Cards.name
                        ),
                        UiComponents(
                            name = HomeComponents.LastTransfers,
                            isShow = true,
                            value = HomeComponents.LastTransfers.name
                        ),
                        UiComponents(
                            name = HomeComponents.FinancesService,
                            isShow = true,
                            value = HomeComponents.FinancesService.name
                        ),
                        UiComponents(
                            name = HomeComponents.ForAdvertising,
                            isShow = true,
                            value = HomeComponents.ForAdvertising.name
                        ),
                        UiComponents(
                            name = HomeComponents.Payments,
                            isShow = true,
                            value = HomeComponents.Payments.name
                        ),
                        UiComponents(
                            name = HomeComponents.PaymentForPhoneNumber,
                            isShow = true,
                            value = HomeComponents.PaymentForPhoneNumber.name
                        ),
                    )
                    list.forEach { item ->
                        putComponents(item)
                    }
                    list
                }
                getUiData()
                }
            }.launchIn(this)
        }
    }

    fun getUiData() {
        viewModelScope.launch {
            components?.let { com ->
                com.forEach { item ->
                    if (item.isShow) {
                        when (item.name) {
                            HomeComponents.Cards -> {
                                // get from remote data
                                _uiData.value = HomeBodyModels.Card(
                                    item.name, listOf(
                                        CardModel("Normurodov", "100 000"),
                                        CardModel("Normurodov", "500 000"),
                                        CardModel("Normurodov", "800 000"),
                                        CardModel("Normurodov", "500 000"),
                                    )
                                )
                            }

                            HomeComponents.Payments -> {
                                // get from local data
                                _uiData.value = HomeBodyModels.Payment(
                                    item.name,
                                    listOf(
                                        PaymentsModel("", R.drawable.bg_beeline_logo),
                                        PaymentsModel("",R.drawable.bg_ucell_logo),
                                        PaymentsModel("",R.drawable.bg_gas_logo),
                                        PaymentsModel("",R.drawable.bg_humo_logo),
                                        PaymentsModel("",R.drawable.bg_uzcard_logo),
                                    )
                                )
                            }

                            HomeComponents.LastTransfers -> {
                                _uiData.value = HomeBodyModels.LastTransfer(
                                    item.name,
                                    listOf(
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                        LastTransferModel(""),
                                    )
                                )
                            }

                            HomeComponents.PaymentForPhoneNumber -> {
                                // it is static ui
                            }

                            HomeComponents.FinancesService -> {
                                // get from local data
                            }

                            HomeComponents.ForAdvertising -> {
                                // get from local data
                            }
                        }
                    }
                }
            }
        }
    }

    private fun putComponents(uiComponents: UiComponents) {
        viewModelScope.launch {
            putComponentsUseCase.putComponents(
                uiComponents = uiComponents
            )
        }
    }

    fun updateComponent(id: Int, isVisibility: Boolean) {
        viewModelScope.launch {
            updateComponentsInCatchUseCase.updateComponents(id, isVisibility)
        }
    }

}