package uz.androbeck.virtualbank.ui.dialogs.card_scanner

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog

@AndroidEntryPoint
class CardScannerByNfcBottomDialog : BaseBottomDialog(R.layout.dialog_bottom_card_scanner_by_nfc) {

    override fun initialize(view: View) {
        view.background = setCornerRadius()
    }
}