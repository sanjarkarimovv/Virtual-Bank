package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    override fun setup() {
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        vm.signUpEvent.onEach {
            println(":::AAAA -> $it")
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.btnLogin.OnClick = {
            println("Clicked button")
            lifecycleScope.launch {
                delay(3000L)
                binding.btnLogin.restoreButton()
            }
        }
    }
}