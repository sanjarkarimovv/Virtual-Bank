package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.full_info

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentUserFullInfoBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class UserFullInfoFragment : BaseFragment(R.layout.fragment_user_full_info) {
    private val binding by viewBinding(FragmentUserFullInfoBinding::bind)
    private val vm: UserFullInfoViewModel by viewModels()
    override fun setup() {
        vm.getUserData()
        changeTitlesAndTextColorsOnFocus()
    }

    override fun observe(): Unit = with(binding) {
        lifecycleScope.launch {
            vm.getUserData().collect { event ->
                when (event) {
                    is UserFullInfoEvent.Error -> {
                        progressBar.gone()
                        println("::: Error -> ${event.massage.toString()}")
                    }

                    UserFullInfoEvent.Loading -> progressBar.visible()


                    is UserFullInfoEvent.Success -> {
                        progressBar.gone()
                        println("::: -> Success User data -> ${event.model}")
                    }
                }
            }
        }
    }

    private fun changeTitlesAndTextColorsOnFocus() = with(binding) {
        nameEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                titleName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
                nameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
            } else {
                titleName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
                nameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
            }
        }
        lastNameEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                titleLastName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
                lastNameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
            } else {
                titleLastName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
                lastNameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
            }

        }
        nickNameEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                titleNickName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
                nickNameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorBlue
                    )
                )
            } else {
                titleNickName.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
                nickNameEt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.colorPrimary
                    )
                )
            }

        }
    }
}