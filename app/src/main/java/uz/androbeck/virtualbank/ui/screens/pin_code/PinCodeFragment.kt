package uz.androbeck.virtualbank.ui.screens.pin_code

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentPinCodeBinding
import uz.androbeck.virtualbank.ui.MainViewModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.pin_code.events.PinCodeEvent
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.vibrate
import uz.androbeck.virtualbank.utils.extentions.visible
import java.util.concurrent.Executors

@AndroidEntryPoint
class PinCodeFragment : BaseFragment(R.layout.fragment_pin_code) {
    private val binding by viewBinding(FragmentPinCodeBinding::bind)
    private val pinCodeViewModel: PinCodeViewModel by viewModels()
    private val sharedVM: MainViewModel by activityViewModels()

    override fun setup() {

    }

    override fun clicks() {
        setupButtonClicks()
        setupFingerprintClick()
    }

    override fun observe() {
        observePinCodeList()
        observeFromRegister()
        observePinCodeEvent()
        observeErrorAttempts()
        observeErrorLogout()
    }

    private fun setupButtonClicks() = with(binding) {
        val buttonIds = listOf(
            binding.btn01 to getString(R.string.str_num_1),
            binding.btn02 to getString(R.string.str_num_2),
            binding.btn03 to getString(R.string.str_num_3),
            binding.btn04 to getString(R.string.str_num_4),
            binding.btn05 to getString(R.string.str_num_5),
            binding.btn06 to getString(R.string.str_num_6),
            binding.btn07 to getString(R.string.str_num_7),
            binding.btn08 to getString(R.string.str_num_8),
            binding.btn09 to getString(R.string.str_num_9),
            binding.btn00 to getString(R.string.str_num_0)
        )

        buttonIds.forEach { (button, digit) ->
            button.setOnClickListener {
                vibrate()
                pinCodeViewModel.addDigit(digit)
            }
        }

        btnRemove.setOnClickListener {
            vibrate()
            pinCodeViewModel.removeLastDigit()
        }

        actionExit.setOnClickListener {
            vibrate()
            pinCodeViewModel.handlePinCodeExit()
            navigateWithDelay(NavGraphEvent.Auth, 500L)
        }
    }

    private fun setupFingerprintClick() {
        binding.btnFingerprint.singleClickable {
            vibrate()
            promptBiometricAuthentication()
        }
    }

    private fun observePinCodeList() {
        pinCodeViewModel.pinCodeList.observe(viewLifecycleOwner) {
            updatePinCode(it)
        }
    }

    private fun observeFromRegister() {
        pinCodeViewModel.fromRegister.observe(viewLifecycleOwner) {
            if (it) {
                if (pinCodeViewModel.checkBiometrics()) {
                    promptBiometricAuthentication()
                    binding.btnFingerprint.visible()
                } else {
                    binding.btnFingerprint.gone()
                }
                binding.actionExit.visible()
            } else {
                binding.actionExit.gone()
                binding.btnFingerprint.gone()
            }
        }
    }

    private fun observePinCodeEvent() {
        pinCodeViewModel.pinCodeEvent.observe(viewLifecycleOwner) {
            handlePinCodeEvent(it)
        }
    }

    private fun observeErrorAttempts() {
        pinCodeViewModel.errorAttempts.observe(viewLifecycleOwner) {
            binding.errorAttempts.text =
                if (it >= 1) getString(R.string.pin_mismatch_message, 5 - it) else ""
        }
    }

    private fun observeErrorLogout() {
        pinCodeViewModel.errorLogout.observe(viewLifecycleOwner) {
            if (it) navigateWithDelay(NavGraphEvent.Auth, 0L)
        }
    }

    private fun handlePinCodeEvent(event: PinCodeEvent) {
        when (event) {
            PinCodeEvent.PinRegistered -> {
                performPinCodeAnimation(false)
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(1200L)
                    findNavController().navigate(R.id.action_pinCodeFragment_to_confirmPinCodeFragment)
                }
            }

            PinCodeEvent.PinValidated -> {
                performPinCodeAnimation(false)
                navigateWithDelay(NavGraphEvent.Main, 1200L)
            }

            PinCodeEvent.PinValidationFailed -> {
                performPinCodeAnimation(true)
            }
        }
    }

    private fun performPinCodeAnimation(isError: Boolean) {
        setButtonsEnabled(false)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(200L)
            pinCodeViewModel.clearPinCode()

            delay(200L)
            checkPinAnim()

            if (isError) {
                delay(600L)
                errorPinAnim()

                delay(1000L)
                pinCodeViewModel.clearPinCode()
                setButtonsEnabled(true)
                binding.errorAttempts.visible()
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

    private fun promptBiometricAuthentication() {
        val biometricPrompt = BiometricPrompt(
            requireActivity(),
            Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    navigateWithDelay(NavGraphEvent.Main, 0L)
                    pinCodeViewModel.resetErrorAttempts()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_prompt_title))
            .setSubtitle(getString(R.string.biometric_prompt_subtitle))
            .setNegativeButtonText(getString(R.string.biometric_prompt_cancel))
            .build()

        viewLifecycleOwner.lifecycleScope.launch {
            if (isAdded && isResumed && !isRemoving && !isDetached) {
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        with(binding) {
            listOf(
                btn01, btn02, btn03,
                btn04, btn05, btn06,
                btn07, btn08, btn09,
                btn00, btnRemove, btnFingerprint
            ).forEach {
                it.isEnabled = enabled
            }
        }
    }
}
