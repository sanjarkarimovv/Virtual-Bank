package uz.androbeck.virtualbank.ui.dialogs.card_options

import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogBottomCardOptionBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.utils.extentions.singleClickable

class CardOptionBottomDialog(
    private val toHistoryScreen: () -> Unit,
    private val toRequisitionScreen: ((CardUIModel)) -> Unit,
    private val toTransferScreen: ((CardUIModel)) -> Unit,
    private val cards: CardUIModel
) : BaseBottomDialog(R.layout.dialog_bottom_card_option) {
    private val binding: DialogBottomCardOptionBinding by viewBinding()

    override fun clicks() = with(binding) {

        tvFillCard.singleClickable {
            findNavController() // to transfer fragment
        }
        tvHistoryCard.singleClickable {
            toHistoryScreen()
            dismiss()

        }
        tvFillCard.singleClickable {
            toTransferScreen(cards)
            dismiss()

        }
        tvRequisites.singleClickable {
            toRequisitionScreen(cards)
            dismiss()

        }
        tvDeleteCard.singleClickable {
            dismiss()

        }
    }

    override fun initialize(view: View) {
        view.background = setCornerRadius()
    }

}