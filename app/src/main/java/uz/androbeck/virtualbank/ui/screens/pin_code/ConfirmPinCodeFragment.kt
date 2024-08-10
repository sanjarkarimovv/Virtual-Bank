package uz.androbeck.virtualbank.ui.screens.pin_code

import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.ui.base.BaseFragment

class ConfirmPinCodeFragment : BaseFragment(R.layout.fragment_confirm_pin_code) {
    override fun setup() {
        arguments?.getString("pinCode")
    }
}