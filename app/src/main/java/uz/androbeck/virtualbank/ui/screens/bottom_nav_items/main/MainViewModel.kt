package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.response.home.fireBaseResDto.GetTvBannerResEvent
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.domain.ui_models.home.PaymentsModel
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.domain.useCases.card.GetCardsUseCase
import uz.androbeck.virtualbank.domain.useCases.history.LastTransfersUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetComponentsFromCacheUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetTotalBalanceUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetTvBannersFromFirebaseUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutComponentsUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.ui.screens.HomeComponents
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val putUpdateInfoUseCase: PutUpdateInfoUseCase,
    private val getComponentsFromCacheUseCase: GetComponentsFromCacheUseCase,
    private val putComponentsUseCase: PutComponentsUseCase,
    private val totalBalanceUseCase: GetTotalBalanceUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val lastTransferUseCase: LastTransfersUseCase,
    private val getTvBannersFromFirebaseUseCase: GetTvBannersFromFirebaseUseCase
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
                                value = "Cards"
                            ),
                            UiComponents(
                                name = HomeComponents.LastTransfers,
                                isShow = true,
                                value = "Last Transfers"
                            ),
                            UiComponents(
                                name = HomeComponents.FinancesService,
                                isShow = true,
                                value = "Finances Service"
                            ),
                            UiComponents(
                                name = HomeComponents.ForAdvertising,
                                isShow = true,
                                value = "Advertising"
                            ),
                            UiComponents(
                                name = HomeComponents.Payments,
                                isShow = true,
                                value = "Payments"
                            ),
                            UiComponents(
                                name = HomeComponents.PaymentForPhoneNumber,
                                isShow = true,
                                value = "Payment for phone number"
                            ),
                        )
                        list.forEach { item ->
                            putComponents(item)
                        }
                        list
                    }
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
                                //get from remote data
                                getCardsUseCase.getCardsFromNetwork().onEach {
                                    _uiData.value = HomeBodyModels.Card(item.name, it)
                                }.launchIn(this)
                            }

                            HomeComponents.Payments -> {
                                // get from local data
                                _uiData.value = HomeBodyModels.Payment(
                                    item.name,
                                    listOf(
                                        PaymentsModel("", R.drawable.bg_beeline_logo),
                                        PaymentsModel("", R.drawable.bg_ucell_logo),
                                        PaymentsModel("", R.drawable.bg_gas_logo),
                                        PaymentsModel("", R.drawable.bg_humo_logo),
                                        PaymentsModel("", R.drawable.bg_uzcard_logo),
                                    ))
                            }

                            HomeComponents.LastTransfers -> {

                                lastTransferUseCase.invoke().onEach {
                                    _uiData.value = HomeBodyModels.LastTransfer(
                                        item.name, it
                                    )
                                }.launchIn(this)
                            }

                            HomeComponents.PaymentForPhoneNumber -> {
                                // it is static ui
                            }

                            HomeComponents.FinancesService -> {
                                // get from local data
                            }

                            HomeComponents.ForAdvertising -> {
                                // get from local data
                                getTvBannersFromFirebaseUseCase.getTvBannerFromFireBase().onEach {
                                    when (it) {
                                        is GetTvBannerResEvent.Error -> {
                                           _uiData.value = HomeBodyModels.Error(it.message)
                                        }

                                        GetTvBannerResEvent.Loading -> {
                                            _uiData.value = HomeBodyModels.Advertising(loading =  true)
                                        }

                                        is GetTvBannerResEvent.Success -> {
                                            _uiData.value = HomeBodyModels.Advertising(it.data)
                                        }
                                    }

                                }.launchIn(this)

                            }
                        }
                    }
                }
            }
            getTotalBalance()
        }
    }

    fun getTotalBalance() {
        viewModelScope.launch {
            totalBalanceUseCase.invoke().onEach {
                _uiData.value = HomeBodyModels.TotalBalance("${it.totalBalance}")
            }.catch {
                _uiData.value = HomeBodyModels.Error(it.message?:"Ko'zda tutilmagan xatolik")
            }.launchIn(this)
        }
    }

    private fun putComponents(uiComponents: UiComponents) {
        viewModelScope.launch {
            putComponentsUseCase.putComponents(
                uiComponents = uiComponents
            )
        }
    }
}