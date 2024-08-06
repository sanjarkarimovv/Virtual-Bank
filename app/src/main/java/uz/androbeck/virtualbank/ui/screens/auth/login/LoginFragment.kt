package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    override fun setup() {
        binding.btnLogin.onClick = {
            findNavController().navigate(
                R.id.action_loginFragment_to_registrationFragment
            )
        }
    }
}