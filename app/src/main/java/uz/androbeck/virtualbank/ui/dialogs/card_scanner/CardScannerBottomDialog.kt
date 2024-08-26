package uz.androbeck.virtualbank.ui.dialogs.card_scanner

import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogButtonCardScannerBinding
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class CardScannerBottomDialog : BaseBottomDialog(R.layout.dialog_button_card_scanner) {
    private val binding by viewBinding(DialogButtonCardScannerBinding::bind)



    override fun initialize(view: View) {
        view.background = setCornerRadius()
    }

    override fun clicks() = with(binding) {
        btnScannerByNfc.singleClickable {
            showCardScannerByNfcDialog()
        }
        btnScannerByCamera.singleClickable{
            findNavController().navigate(R.id.action_addCardFragment_to_cardScannerFragment)
        }
    }

    private fun showCardScannerByNfcDialog() {
        CardScannerByNfcBottomDialog().show(
            childFragmentManager,
            CardScannerByNfcBottomDialog::class.java.name
        )
    }
}