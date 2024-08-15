package uz.androbeck.virtualbank.ui.dialogs.show_cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.home.CardModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

@HiltViewModel
class ShowCardsViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : ViewModel() {

    private val _uiEvent = Channel<ShowDialogUiEvent>()
    val uiEvent = _uiEvent.consumeAsFlow()

    init {
        viewModelScope.launch {
            _uiEvent.send(
                ShowDialogUiEvent.AllCards(
                    listOf(
                        CardModel(
                            cardStile = preferencesProvider.cardStile,
                            name = "Androbek",
                            amount = "100 000"
                        ),
                        CardModel(
                            cardStile = preferencesProvider.cardStile,
                            name = "Androbek",
                            amount = "100 000"
                        ),
                        CardModel(
                            cardStile = preferencesProvider.cardStile,
                            name = "Androbek",
                            amount = "100 000"
                        ),
                    )
                )
            )
        }
    }
}