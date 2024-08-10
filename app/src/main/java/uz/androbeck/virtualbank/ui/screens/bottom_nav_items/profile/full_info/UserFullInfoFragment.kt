package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.full_info

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentUpdateProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class UserFullInfoFragment : BaseFragment(R.layout.fragment_update_profile) {
    private val binding by viewBinding(FragmentUpdateProfileBinding::bind)
    private val vm: UserFullInfoViewModel by viewModels()
    override fun setup() = with(binding) {
        vm.getUserData()
    }

    override fun observe() {
        vm.getUserData.onEach { event ->
            when (event) {
                is UserFullInfoEvent.Error -> println("::: Error -> ${event.massage.toString()}")
                UserFullInfoEvent.Loading -> println("::: -> Loading Model...")
                is UserFullInfoEvent.Success -> {
                    println("::: -> Success User data -> ${event.model}")
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun clicks() = with(binding) {
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_userFullInfoFragment_to_profileFragment)
        }
    }
}