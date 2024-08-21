package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
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
        viewLifecycleOwner.lifecycleScope.launch {
            println("loading...")
            vm.transferVerifyApiCall(
                CodeVerifyReqDto(
                    prefs.token,
                    "56216"
                )
            ).collect {
                when (it) {
                    is Event.Error -> {
                        println("Error -> ${it.message.toString()}")
                    }

                    is Event.Success -> {
                        println("Success -> ${it.message.toString()}")
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