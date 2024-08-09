package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    private val mainSharedVM: MainSharedViewModel by activityViewModels()
    override fun setup() {

        vm.fullInfoEvent.onEach { fullInfo->
            val user=fullInfo.firstName+" "+fullInfo.lastName
            binding.user.text=user
        }
        binding.security.setOnClickListener {
            mainSharedVM.setNavGraphEvent(NavGraphEvent.Security)
        }

    }
}