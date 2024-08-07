package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    override fun setup() {



        vm.fullInfoEvent.onEach { fullInfo->
            val user=fullInfo.firstName+" "+fullInfo.lastName
            Toast.makeText(requireContext(), " AAA $user", Toast.LENGTH_SHORT).show()
            binding.user.text=user
        }


    }
}