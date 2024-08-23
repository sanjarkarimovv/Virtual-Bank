package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogBottomCardsBinding
import uz.androbeck.virtualbank.domain.useCases.card.GetCardsUseCase
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import javax.inject.Inject

@AndroidEntryPoint
class CardsDialog:BaseBottomDialog(R.layout.dialog_bottom_cards) {
    private lateinit var bottomAdapter: BottomDialogAdapter
    @Inject
    lateinit var getCardsUseCase: GetCardsUseCase
    private val binding: DialogBottomCardsBinding by viewBinding()
    override fun setup(): Unit = with(binding){
        lifecycleScope.launch {
            getCardsUseCase().collect{cardUiModel->
                bottomAdapter= BottomDialogAdapter(cardUiModel)
                rvCards.layoutManager=LinearLayoutManager(requireContext())
                rvCards.adapter=bottomAdapter
            }
        }
    }

    override fun initialize(view: View) {
        super.initialize(view)
        view.background = setCornerRadius()
    }
}