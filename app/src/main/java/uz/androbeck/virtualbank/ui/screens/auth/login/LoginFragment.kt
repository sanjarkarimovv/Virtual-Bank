package uz.androbeck.virtualbank.ui.screens.auth.login

import android.content.res.Resources
import android.graphics.Rect
import android.text.InputType
import android.view.ViewGroup
import android.view.ViewTreeObserver
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.ui.MainViewModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.enter_verify_code.EnterVerifyCodeDialogFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.Screen
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.PHONE_NUMBER_FOR_VERIFY
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.SCREEN
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.TOKEN_FOR_VERIFY
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    private val sharedVM: MainViewModel by activityViewModels()
    private var signInReqUIModel: SignInReqUIModel? = null
    private var enterVerifyCodeDialogFragment: EnterVerifyCodeDialogFragment? = null
    private var isShowPassword = false
    private var isKeyboardVisible = false
    override fun setup() {
        with(binding) {
            btnLogin.isEnable = false
            etPhoneNumber.addTextChangedListener {
                access()
            }
            etPassword.addTextChangedListener {
                access()
            }
        }
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content).viewTreeObserver.addOnGlobalLayoutListener(
            on
        )
    }

    override fun clicks() = with(binding) {
        btnLogin.onClick = {
            signInReqUIModel?.let {
                vm.signIn(it)
            }
        }
        btnSignUp.singleClickable {
            findNavController().navigate(R.id.action_loginFragment_to_registrationAddPersonalInfoFragment)
        }
        btnBack.singleClickable {
            findNavController().popBackStack()
        }
        btnPassword.singleClickable {
            isShowPassword = !isShowPassword
            togglePasswordVisibility(etPassword, btnPassword, isShowPassword)
        }
    }

    private fun access() = with(binding) {
        val phone = formatPhoneNumber(etPhoneNumber.text?.toString().orEmpty())
        val password = etPassword.text?.toString()
        vm.accessCheck(
            SignInReqUIModel(
                phone = phone,
                password = password
            )
        )
    }

    override fun observe(): Unit = with(viewLifecycleOwner.lifecycleScope) {
        vm.accessLogin.onEach { (isValidate, _, signInReqUIModel) ->
            when {
                !isValidate -> {
                    binding.btnLogin.isEnable = false
                }

                isValidate -> {
                    this@LoginFragment.signInReqUIModel = signInReqUIModel
                    binding.btnLogin.isEnable = true
                }
            }
        }.launchIn(this)

        vm.signInEvent.onEach {
            when (it) {
                is LoginUiEvent.Error -> {
                    toast(it.massage ?: getString(R.string.str_error_unexpected))
                    binding.btnLogin.isProgress = false
                }

                LoginUiEvent.Loading -> {
                    binding.btnLogin.isProgress = true
                }

                is LoginUiEvent.Success -> {
                    binding.btnLogin.isProgress = false
                    toast(getString(R.string.str_success))
                    showVerifyDialog(it.token)
                }
            }
        }.catch {
            toast(it.message ?: getString(R.string.str_error_unexpected))
        }.launchIn(this)
    }

    private fun showVerifyDialog(token: String? = null) {
        enterVerifyCodeDialogFragment = EnterVerifyCodeDialogFragment()
        // token key
        enterVerifyCodeDialogFragment?.arguments = bundleOf(
            TOKEN_FOR_VERIFY to token,
            PHONE_NUMBER_FOR_VERIFY to signInReqUIModel?.phone.toString(),
            SCREEN to Screen.LOGIN.name
        )
        enterVerifyCodeDialogFragment?.show(
            childFragmentManager,
            EnterVerifyCodeDialogFragment::class.java.simpleName
        )
        enterVerifyCodeDialogFragment?.onSuccessVerify = {
            sharedVM.setNavGraphEvent(NavGraphEvent.PinCode)
        }
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
    private val on = ViewTreeObserver.OnGlobalLayoutListener {
        if (view == null || !isAdded) return@OnGlobalLayoutListener

        val rect = Rect()
        val rootView =
            requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height
        val keypadHeight = screenHeight - rect.bottom

        val isKeyboardNowVisible = keypadHeight > screenHeight * 0.15

        if (isKeyboardNowVisible != isKeyboardVisible) {
            isKeyboardVisible = isKeyboardNowVisible
            onKeyboardVisibilityChanged(isKeyboardVisible, keypadHeight)
        }
    }


    private fun onKeyboardVisibilityChanged(visible: Boolean, keypadHeight: Int) {
        if (view == null || !isAdded) return

        val params = binding.ll.layoutParams as ViewGroup.MarginLayoutParams
        if (visible) {
            params.bottomMargin = keypadHeight + binding.ll.height
        } else {
            params.bottomMargin = 20.dpToPx()
        }
        binding.ll.layoutParams = params
    }

    private fun Int.dpToPx(): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (this * density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
            .viewTreeObserver.removeOnGlobalLayoutListener(on)
    }

    override fun onDestroy() {
        super.onDestroy()
        enterVerifyCodeDialogFragment = null
    }
}


