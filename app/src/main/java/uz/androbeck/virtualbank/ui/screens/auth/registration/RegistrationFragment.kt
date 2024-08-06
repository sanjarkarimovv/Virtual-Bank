package uz.androbeck.virtualbank.ui.screens.auth.registration

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentRegistrationBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

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

    private var requestModel: SignUpReqUIModel? = null

    override fun setup() {
        binding.btnSignUp.isEnable = false
    }

    override fun clicks(): Unit = with(binding) {
        btnSignUp.onClick = {
            btnSignUp.isProgress = true
            requestModel?.let(vm::signUp)
            requestModel = null
        }

        etFirstName.editText.addTextChangedListener {
            accessSignUp()
        }
        etLastName.editText.addTextChangedListener {
            accessSignUp()
        }
        etPassword.editText.addTextChangedListener {
            accessSignUp()
        }
        etConfirmPassword.editText.addTextChangedListener {
            accessSignUp()
        }
        etPhoneNumber.editText.addTextChangedListener {
            accessSignUp()
        }
        etDate.editText.addTextChangedListener {
            accessSignUp()
        }
    }

    private fun accessSignUp() = with(binding) {
        val firstName = etFirstName.editText.text?.toString().orEmpty()
        val lastName = etLastName.editText.text?.toString().orEmpty()
        val password = etPassword.editText.text?.toString().orEmpty()
        val confirmPassword = etConfirmPassword.editText.text?.toString().orEmpty()
        val phoneNumber = etPhoneNumber.editText.text?.toString().orEmpty()
        val bornDate = etDate.editText.text?.toString().orEmpty()
        val gender = if (cbGender.isChecked) 0 else 1
        val requestModel = SignUpReqUIModel(
            firstName = firstName,
            lastName = lastName,
            password = password,
            phone = phoneNumber,
            bornDate = bornDate,
            gender = gender
        )
        vm.accessSignUp(requestModel, confirmPassword)
    }

    override fun observe(): Unit = with(binding) {
        with(vm) {
            with(viewLifecycleOwner.lifecycleScope) {
                accessSignUp.onEach { (isAccess, message, requestModel) ->
                    btnSignUp.isEnable = isAccess
                    if (!isAccess && message.isNotEmpty()) {
                        println(message)
                        return@onEach
                    }
                    if (isAccess) {
                        this@RegistrationFragment.requestModel = requestModel
                    }
                }.launchIn(this)

                signUpEvent.onEach {
                    btnSignUp.isProgress = false
                    //TODO Sanjar sign up verify dialogni open qilasiz
                }.launchIn(this)
            }
        }

        messageController.observeMessage().onEach {
            btnSignUp.isProgress = false
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        requestModel = null
    }
}