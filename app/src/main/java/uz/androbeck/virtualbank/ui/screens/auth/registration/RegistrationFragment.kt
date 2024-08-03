package uz.androbeck.virtualbank.ui.screens.auth.registration

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentRegistrationBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import uz.androbeck.virtualbank.ui.screens.auth.login.logics.WEText
import uz.androbeck.virtualbank.utils.extentions.invisible
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import javax.inject.Inject

// +99899-856-42-50

//Hello Virtual Bank
@AndroidEntryPoint
class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val sharedVM: MainSharedViewModel by activityViewModels()
    private val vm by viewModels<RegistrationViewModel>()

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    @Inject
    lateinit var messageController: MessageController


    override fun setup() {
        binding.btnSignUp.invisible()
        WEText(
            listOf(
                binding.etFirstName,
                binding.etLastName,
                binding.etPassword,
                binding.etConfirmPassword,
                binding.etPhoneNumber,
                binding.etDate
            )
        ).listener().observe(viewLifecycleOwner) {
            when (it) {
                is WEText.ETEvent.Success -> {
                    binding.btnSignUp.visible()
                    toast(it.massage)
                }
                is WEText.ETEvent.Error -> {
                    toast(it.massage) //berilgan index dagi view ni qaytaradi
                }

                is WEText.ETEvent.SuccessItem -> {
                    toast(it.massage) //berilgan index dagi view ni qaytaradi
                }
            }
        }
    }

    override fun clicks(): Unit = with(binding) {
        btnSignUp.onClickProgress(isProgress = false) {
            if (binding.etConfirmPassword.text.toString() == binding.etPassword.text.toString()) {
                // success ruxsat bor
                toast("ruxsat bor")
              //  accessSignUp()

            } else {
                Toast.makeText(requireContext(), "ikkinchi patol xato", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun observe() {}

}