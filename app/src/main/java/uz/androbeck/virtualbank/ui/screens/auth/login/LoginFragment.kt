package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentLoginBinding
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModels()
    override fun setup() {
        binding.btnLogin.onClick =  {
            findNavController().navigate(
                R.id.action_loginFragment_to_registrationFragment
            )
        }
        vm.signInEvent.onEach {
            println(":::: BBBBBBBBBBBBB $it")
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        binding.btnLogin.OnClick = {
            vm.signIn(
                SignInReqUIModel(
                    phone = "998991112233", password = "1234qwer"
                )
            )
        }
    }
}