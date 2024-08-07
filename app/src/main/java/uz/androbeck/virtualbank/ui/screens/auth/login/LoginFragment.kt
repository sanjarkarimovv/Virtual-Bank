package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.dialog_enter_verify_code.EnterVerifyCodeDialogFragment
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    private var model: SignInReqUIModel? = null
    override fun setup() {
        binding.btnLogin.isEnable = false
        with(binding) {
            etPhone.textInputEditText.addTextChangedListener {
                access()
            }
            etPassword.textInputEditText.addTextChangedListener {
                access()
            }
        }
        LoginUiEvent.Loading
    }

    override fun clicks() = with(binding) {
        btnLogin.setOnClickListener {
            model?.let {
                vm.signIn(it)
            }
        }
        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
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
        vm.accessLogin.onEach {
            when {
                !it.first -> {
                    binding.btnLogin.isEnable = false
                }
                it.first -> {
                    setModel(it.third)
                }
            }
        }.launchIn(this)

        vm.signInEvent.onEach {
            when (it) {
                is LoginUiEvent.Error -> {
                    toast(it.massage ?: "Null")
                    binding.btnLogin.isProgress = false
                }

                LoginUiEvent.Loading -> {
                    binding.btnLogin.isProgress = true
                }

                is LoginUiEvent.Success -> {
                    binding.btnLogin.isProgress = false
                    // show password set dialog !
                    toast("Success")
                    val dialogFragment = EnterVerifyCodeDialogFragment()
                    // token key
                    dialogFragment.arguments?.putString("token", it.token)
                    dialogFragment.show(childFragmentManager, null)
                }
            }
        }.catch {
            toast(it.message ?: "Ko'zda tutilmagan xatolik")
        }.launchIn(this)

    }

    private fun setModel(data: SignInReqUIModel?) = CoroutineScope(Dispatchers.Main).launch {
        model = data
        binding.btnLogin.isEnable = true
    }
}


