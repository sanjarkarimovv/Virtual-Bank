package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.change_language.ChangeLanguageBottomDialog
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    private var userModel: FullInfoUIModel? = null

    override fun setup(): Unit {
        vm.getUserData()
    }

    override fun clicks() = with(binding) {
        appLanguage.singleClickable {
            val dialog = ChangeLanguageBottomDialog.show(childFragmentManager)
            dialog.onLanguageChanged = {
                findNavController().navigate(R.id.recreate_profile)
            }
        }
        tvUser.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_updateInfoFragment,
                bundleOf(Constants.ArgumentKey.USER_FULL_INFO to userModel)
            )
        }
    }

    override fun observe(): Unit = with(binding) {
        lifecycleScope.launch {
            vm.getUserData().collect { event ->
                when (event) {
                    is ProfileFragmentEvent.Error -> {
                        println("::: Error -> ${event.massage.toString()}")
                    }

                    ProfileFragmentEvent.Loading -> println("User Informations Loading...")

                    is ProfileFragmentEvent.Success -> {
                        event.model?.let {
                            userModel = it
                        }
                        println("::: -> Success User data -> ${event.model}")
                        val fullName = "${event.model?.firstName} ${event.model?.lastName}"
                        tvUser.text = fullName
                    }
                }
            }
        }
    }
}