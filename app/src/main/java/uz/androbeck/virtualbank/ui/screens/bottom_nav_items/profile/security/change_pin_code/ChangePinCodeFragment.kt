package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.change_pin_code

import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentChangePincodeBinding
import uz.androbeck.virtualbank.ui.MainViewModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events.ChangePinAnimEvent
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events.ChangePinCodeEvent
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.vibrate
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class ChangePinCodeFragment : BaseFragment(R.layout.fragment_change_pincode) {
    private val binding by viewBinding(FragmentChangePincodeBinding::bind)
    private val pinCodeViewModel: ChangePinCodeViewModel by viewModels()
    private val sharedVM: MainViewModel by activityViewModels()
    override fun setup() {
    }

    override fun clicks() {
        setupButtonClicks()
    }

    override fun observe() {
        observePinCodeList()
        observeErrorLogout()
        observePinCodeEvent()
        observeErrorAnim()
    }

    private fun setupButtonClicks() = with(binding) {
        val buttonIds = listOf(
            btn01 to getString(R.string.str_num_1),
            btn02 to getString(R.string.str_num_2),
            btn03 to getString(R.string.str_num_3),
            btn04 to getString(R.string.str_num_4),
            btn05 to getString(R.string.str_num_5),
            btn06 to getString(R.string.str_num_6),
            btn07 to getString(R.string.str_num_7),
            btn08 to getString(R.string.str_num_8),
            btn09 to getString(R.string.str_num_9),
            btn00 to getString(R.string.str_num_0)
        )

        buttonIds.forEach { (button, digit) ->
            button.setOnClickListener {
                vibrate()
                pinCodeViewModel.addDigit(digit)
            }
        }

        pinToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_changePinCodeFragment_to_securityFragment)
        }

        btnRemove.setOnClickListener {
            vibrate()
            pinCodeViewModel.removeLastDigit()
        }

        listOf(btnConfirm, toolbarText).forEach { button ->
            button.setOnClickListener {
                vibrate()
                pinCodeViewModel.confirmPinValidation()
            }
        }
    }

    private fun observePinCodeList() {
        pinCodeViewModel.pinCodeList.observe(viewLifecycleOwner) {
            updatePinCode(it)
        }
    }

    private fun observeErrorLogout() {
        pinCodeViewModel.errorLogout.observe(viewLifecycleOwner) {
            if (it) navigateWithDelay(NavGraphEvent.Auth, 2000L)
        }
    }

    private fun observePinCodeEvent() {
        pinCodeViewModel.pinCodeProgress.observe(viewLifecycleOwner) {
            when (it) {
                ChangePinCodeEvent.PinCheck -> {
                    binding.toolbarText.gone()
                    binding.btnConfirm.gone()
                    binding.pinActionText.text = getString(R.string.pin_code_page_enter_pin)
                }

                ChangePinCodeEvent.PinSet -> {
                    binding.toolbarText.gone()
                    binding.btnConfirm.gone()
                    binding.pinActionText.text = getString(R.string.str_new_pin_code)
                }

                ChangePinCodeEvent.PinValidate -> {
                    binding.toolbarText.visible()
                    binding.btnConfirm.visible()
                    binding.pinActionText.text = getString(R.string.str_confirm_new_pin_code)
                }

                ChangePinCodeEvent.PinSuccess -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        delay(100L)
                        findNavController().navigate(R.id.action_changePinCodeFragment_to_securityFragment)
                    }

                }
            }
        }
    }

    private fun observeErrorAnim() {
        pinCodeViewModel.errorAnim.observe(viewLifecycleOwner) {
            when (it) {
                ChangePinAnimEvent.PinValidated -> {
                    performPinCodeAnimation(false)
                }

                ChangePinAnimEvent.PinNotValidated -> {
                    performPinCodeAnimation(true)
                }

                ChangePinAnimEvent.PinNeutral -> {
                    //Do nothing
                }
            }
        }
    }

    private fun performPinCodeAnimation(isError: Boolean) {
        setButtonsEnabled(false)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(200L)
            pinCodeViewModel.clearPinCode()

            delay(300L)
            checkPinAnim()

            if (isError) {
                delay(600L)
                errorPinAnim()

                delay(1000L)
                pinCodeViewModel.clearPinCode()
                setButtonsEnabled(true)
            } else {
                delay(900L)
                pinCodeViewModel.clearPinCode()
                setButtonsEnabled(true)
            }
        }
    }

    private fun updatePinCode(pinCode: MutableList<String>) {
        with(binding) {
            val pinViews = listOf(view01, view02, view03, view04)
            pinViews.forEachIndexed { index, view ->
                view.setBackgroundResource(
                    if (index < pinCode.size) R.drawable.bg_pin_active
                    else R.drawable.bg_pin_inactive
                )
            }

            if (pinCode.size == 4) {
                pinCodeViewModel.handlePinCodeCompletion()
            }
        }
    }

    private fun navigateWithDelay(event: NavGraphEvent, delay: Long) {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(delay)
            sharedVM.setNavGraphEvent(event)
        }
    }

    private fun checkPinAnim() {
        val pinViews = listOf(binding.view01, binding.view02, binding.view03, binding.view04)
        pinViews.forEachIndexed { index, view ->
            viewLifecycleOwner.lifecycleScope.launch {
                delay(index * 100L)
                view.setBackgroundResource(R.drawable.bg_pin_active)
            }
        }
    }

    private fun errorPinAnim(
        view: View = binding.root,
        offset: Float = 100f,
        repeatCount: Int = 4,
        dampingRatio: Float? = null,
        duration: Long = 1000L,
        interpolator: Interpolator = AccelerateDecelerateInterpolator()
    ) {
        val defaultDampingRatio = dampingRatio ?: (1f / (repeatCount + 1))
        val animValues = List((repeatCount * 4) + 1) { i ->
            when (i % 4) {
                0 -> 0f
                1 -> -offset * (1 - defaultDampingRatio * (i / 4))
                2 -> 0f
                3 -> offset * (1 - defaultDampingRatio * (i / 4))
                else -> 0f
            }
        }.toFloatArray()

        ValueAnimator.ofFloat(*animValues).apply {
            addUpdateListener { view.translationX = it.animatedValue as Float }
            this.interpolator = interpolator
            this.duration = duration
            start()
        }
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        with(binding) {
            listOf(
                btn01, btn02, btn03,
                btn04, btn05, btn06,
                btn07, btn08, btn09,
                btn00, btnRemove
            ).forEach {
                it.isEnabled = enabled
            }
        }
    }
}