package uz.androbeck.virtualbank.ui.screens.auth.login

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
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.enter_verify_code.EnterVerifyCodeDialogFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.MainViewModel
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
    override fun setup() {
        with(binding) {
            btnLogin.isEnable = false
            etPhone.textInputEditText.addTextChangedListener {
                access()
            }
            etPassword.textInputEditText.addTextChangedListener {
                access()
            }
        }
    }

    override fun clicks() = with(binding) {
        btnLogin.onClick = {
            signInReqUIModel?.let {
                vm.signIn(it)
            }
        }
        btnSignUp.onClick = {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        customToolbar.onClickLeftIcon = {
            findNavController().popBackStack()
        }
    }

    private fun access() = with(binding) {
        val phone = etPhone.textInputEditText.text?.toString()
        val password = etPassword.textInputEditText.text?.toString()
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
                    println(it.massage)
                    println(it)
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

    override fun onDestroy() {
        super.onDestroy()
        enterVerifyCodeDialogFragment = null
    }
}


