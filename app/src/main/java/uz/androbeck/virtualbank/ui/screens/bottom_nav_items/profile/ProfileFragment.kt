package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
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

        println("profile fragment is in force ")
        vm.transferVerifyApiCall(
            CodeVerifyReqDto(
                prefs.token,
                "142392"
            )
        ).onEach { event ->
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

    override fun clicks() = with(binding) {
        appLanguage.singleClickable {
            val dialog = ChangeLanguageBottomDialog.show(childFragmentManager)
            dialog.onLanguageChanged = {
                findNavController().navigate(R.id.recreate_profile)
            }
        }
    }
}