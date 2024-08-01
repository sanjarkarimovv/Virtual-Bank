package uz.androbeck.virtualbank.ui.screens.auth.registration

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
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.base.Resource
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import javax.inject.Inject

//Hello Virtual Bank
@AndroidEntryPoint
class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val sharedVM: MainSharedViewModel by activityViewModels()
    private val vm by viewModels<RegistrationViewModel>()

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    override fun setup() {
        vm.signUp(
            SignUpReqUIModel(
                phone = "+998998564250",
                password = "1234qwer",
                firstName = "Sanjar",
                lastName = "Karimov",
                bornDate = "2132432",
                gender = "1"
            )
        )

        vm.errorMessage.onEach {
            binding.progessBar.gone()
            toast(it.orEmpty())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        vm.signUpEvent.onEach {
            when (it) {
                Resource.Loading -> {
                    binding.progessBar.visible()
                }

                is Resource.Success<*> -> {
                    binding.progessBar.gone()
                    (it as? TokenUIModel)?.let {
                        binding.tvData.text = it.toString()
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}