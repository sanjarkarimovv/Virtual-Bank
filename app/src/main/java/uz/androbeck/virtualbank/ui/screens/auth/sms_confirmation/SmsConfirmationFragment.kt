package uz.androbeck.virtualbank.ui.screens.auth.sms_confirmation

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSmsConfirmationBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
@AndroidEntryPoint
class SmsConfirmationFragment : BaseFragment(R.layout.fragment_sms_confirmation) {
    private val binding: FragmentSmsConfirmationBinding by viewBinding()
    private val vm: SmsConfirmationViewModel by viewModels()
    override fun setup() {
        vm.signUpVerifyEvent.onEach {
            println(":::::::::::SignUpVerifyEvent Fragment-> $it")
        }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}