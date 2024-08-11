package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.full_info

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentUserFullInfoBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class UserFullInfoFragment : BaseFragment(R.layout.fragment_user_full_info) {
    private val binding by viewBinding(FragmentUserFullInfoBinding::bind)
    private val vm: UserFullInfoViewModel by viewModels()
    override fun setup() = with(binding) {
        vm.getUserData()
        changeTitlesColorOnFocus()
    }

    override fun observe(): Unit = with(binding) {
        vm.getUserData.onEach { event ->
            when (event) {
                is UserFullInfoEvent.Error -> {
                    println("::: Error -> ${event.massage.toString()}")
                }

                UserFullInfoEvent.Loading -> {}

                is UserFullInfoEvent.Success -> {
                    println("::: -> Success User data -> ${event.model}")
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun changeTitlesColorOnFocus() = with(binding) {
        nameEt.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {
                    titleName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorBlue
                        )
                    )
                }

                false -> {
                    titleName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )
                }
            }
        }
        lastNameEt.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {
                    titleLastName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorBlue
                        )
                    )
                }

                false -> {
                    titleLastName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )
                }
            }
        }
        nickNameEt.setOnFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                true -> {
                    titleNickName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorBlue
                        )
                    )
                }

                false -> {
                    titleNickName.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )
                }
            }
        }
    }
}