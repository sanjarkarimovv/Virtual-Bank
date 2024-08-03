package uz.androbeck.virtualbank.ui.screens.auth.login

import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.auth.login.logics.WEText
import uz.androbeck.virtualbank.utils.extentions.invisible
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    override fun setup() {
        binding.btnLogin.onClickProgress(isProgress = true) {
            findNavController().navigate(
                R.id.action_loginFragment_to_registrationFragment
            )
        }
        binding.btnLogin.invisible()
        val list = listOf(binding.etPhone.textInputEditText, binding.etPassword.textInputEditText)
        WEText<EditText>(list).listener().observe(viewLifecycleOwner) {
            when (it) {
                is WEText.ETEvent.Error -> {
                    toast(it.massage)
                    binding.btnLogin.invisible()
                }

                is WEText.ETEvent.SuccessItem -> {
                    toast(it.massage)
                }

                is WEText.ETEvent.Success -> {
                    toast(it.massage)
                    binding.btnLogin.visible()
                }
            }
        }
    }
}