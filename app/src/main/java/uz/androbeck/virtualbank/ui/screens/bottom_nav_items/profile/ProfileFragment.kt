package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.change_language.ChangeLanguageBottomDialog
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()

    @Inject
    lateinit var prefs: PreferencesProvider

    override fun setup() {
        println("profile fragment is in force ")
        viewLifecycleOwner.lifecycleScope.launch {
            println("loading...")
            vm.transferVerifyApiCall(
                CodeVerifyReqDto(
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMTI3LjAuMC4xOjgwODQvbW9iaWxlLWJhbmsvdjEvYXV0aCIsImlzcyI6Imh0dHA6Ly8xMjcuMC4wLjE6ODA4NC9tb2JpbGUtYmFuayIsInBob25lIjoiOTc1OTUwNjI3IiwiY29kZSI6Ijc4MjYxMSIsImV4cCI6MTcyNDEwMzYxNH0.12TxqAQz2n09y9PHlBilG3AyCjxzqiJ_jm4T1F7fA48",
                    "71237"
                )
            ).collect { event ->
                println("Events")
                when (event) {
                    is Event.Error -> {
                        println(event.message.toString())
                    }

                    is Event.Success -> {
                        println(event.message.toString())
                    }
                }
            }
        }
    }

    override fun clicks() = with(binding) {
        appLanguage.singleClickable {
            val dialog = ChangeLanguageBottomDialog.show(childFragmentManager)
            dialog.onLanguageChanged = {
                findNavController().navigate(R.id.recreate_profile)
            }
        }
    }
}