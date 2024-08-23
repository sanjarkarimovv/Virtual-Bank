package uz.androbeck.virtualbank.ui.screens.auth.registration

import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentRegistrationBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainViewModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.enter_verify_code.EnterVerifyCodeDialogFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.Screen
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.PHONE_NUMBER_FOR_VERIFY
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.SCREEN
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.TOKEN_FOR_VERIFY
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_BORN_DATE
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_FIRST_NAME
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_GENDER
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_LAST_NAME
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val sharedVM: MainViewModel by activityViewModels()
    private val vm by viewModels<RegistrationViewModel>()
    private var isShowPassword = false
    private var isShowConfirmPassword = false

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    @Inject
    lateinit var messageController: MessageController

    private var requestModel: SignUpReqUIModel? = null

    private var enterVerifyCodeDialog: EnterVerifyCodeDialogFragment? = null

    override fun setup() {
        binding.btnSignUp.isEnable = false
    }

    override fun clicks(): Unit = with(binding) {
        btnBack.singleClickable {
            findNavController().popBackStack()
        }
        btnSignUp.onClick = {
            showProgress()
            requestModel?.let(vm::signUp)
        }
        btnPassword.singleClickable {
            isShowPassword = !isShowPassword
            togglePasswordVisibility(etPassword, btnPassword, isShowPassword)
        }

        btnConfirmPassword.singleClickable {
            isShowConfirmPassword = !isShowConfirmPassword
            togglePasswordVisibility(etConfirmPassword, btnConfirmPassword, isShowConfirmPassword)
        }
        etPassword.addTextChangedListener {
            accessSignUp()
        }
        etConfirmPassword.addTextChangedListener {
            accessSignUp()
        }
        etPhoneNumber.addTextChangedListener {
            accessSignUp()
        }
    }

    private fun accessSignUp() = with(binding) {
        val firstName = arguments?.getString(USER_FIRST_NAME)
        val lastName = arguments?.getString(USER_LAST_NAME)
        val bornDate = arguments?.getString(USER_BORN_DATE)
        val gender = arguments?.getInt(USER_GENDER)
        val password = etPassword.text?.toString().orEmpty()
        val confirmPassword = etConfirmPassword.text?.toString().orEmpty()
        val phoneNumber = formatPhoneNumber(etPhoneNumber.text?.toString().orEmpty())
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
                    hideProgress()
                    showVerifyCodeDialog(it)
                }.launchIn(this)
            }
        }
        messageController.observeMessage().onEach {
            hideProgress()
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showVerifyCodeDialog(token: String) {
        enterVerifyCodeDialog = EnterVerifyCodeDialogFragment()
        enterVerifyCodeDialog?.arguments = bundleOf(
            TOKEN_FOR_VERIFY to token,
            PHONE_NUMBER_FOR_VERIFY to formatPhoneNumber(binding.etPhoneNumber.text.toString()),
            SCREEN to Screen.REGISTRATION.name
        )
        enterVerifyCodeDialog?.show(
            childFragmentManager, EnterVerifyCodeDialogFragment::class.java.simpleName
        )
        enterVerifyCodeDialog?.onSuccessVerify = {
            sharedVM.setNavGraphEvent(NavGraphEvent.PinCode)
        }
    }

    private fun showProgress() {
        binding.btnSignUp.isProgress = true
    }

    private fun hideProgress() {
        binding.btnSignUp.isProgress = false
    }

    private fun togglePasswordVisibility(
        editText: EditText,
        button: ImageView,
        isVisible: Boolean,
    ) {
        val inputType = if (isVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        editText.inputType = inputType
        button.setImageResource(if (isVisible) R.drawable.ic_password_show else R.drawable.ic_password_hide)
        editText.setSelection(editText.text?.length ?: 0)
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        return phoneNumber.replace("\\D".toRegex(), "").let {
            "${getString(R.string.str_phone_region_code)}$it"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requestModel = null
        enterVerifyCodeDialog = null
    }
}