package uz.androbeck.virtualbank.ui.screens.pin_code

import android.animation.ValueAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.biometric.BiometricPrompt
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentPinCodeBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainViewModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.vibrate
import uz.androbeck.virtualbank.utils.extentions.visible
import java.util.concurrent.Executors
import javax.inject.Inject

@AndroidEntryPoint
class PinCodeFragment : BaseFragment(R.layout.fragment_pin_code) {
    private val binding by viewBinding(FragmentPinCodeBinding::bind)
    private val pinCodeViewModel: PinCodeViewModel by viewModels()
    private val sharedVM: MainViewModel by activityViewModels()

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    override fun setup() {
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
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

            btnRemove.setOnClickListener {
                vibrate()
                pinCodeViewModel.removeLastDigit()
            }

//            listOf(btnConfirm, actionConfirm).forEach { button ->
//                button.setOnClickListener {
//                    vibrate()
//                    pinCodeViewModel.handlePinCodeCompletion()
//                }
//            }

            actionExit.setOnClickListener {
                vibrate()
                pinCodeViewModel.handlePinCodeExit()
                navigateWithDelay(NavGraphEvent.Auth, 500)
            }

            btnFingerprint.singleClickable {
                vibrate()
                promptBiometricAuthentication()
            }
        }
    }

    private fun setupObservers() {
        pinCodeViewModel.apply {
            pinCodeList.observe(viewLifecycleOwner) {
                updatePinCode(it)
            }

            fromRegister.observe(viewLifecycleOwner) {
                with(binding) {
                    if (it == false) {
                        actionExit.gone()
                    } else {
                        if (pinCodeViewModel.checkBiometrics()) { // mana bu logikani confirm pin codega qo'shib yuborman
                            promptBiometricAuthentication()
                            btnFingerprint.visible()
                            actionExit.visible()
                        } else {
                            btnFingerprint.gone()
                            actionExit.visible()
                        }
                    }
                }
            }

            pinCodeEvent.observe(viewLifecycleOwner) {
                handlePinCodeEvent(it)
            }

            errorAttempts.observe(viewLifecycleOwner) {
                binding.errorAttempts.text =
                    if (it >= 1) getString(R.string.pin_mismatch_message, 5 - it) else ""
            }

            errorLogout.observe(viewLifecycleOwner) {
                if (it) {
                    navigateWithDelay(NavGraphEvent.Auth, 0)
                }
            }
        }
    }

    private fun handlePinCodeEvent(event: PinCodeEvent) {
        when (event) {
            is PinCodeEvent.PinRegistered -> {
                setButtonsEnabled(false)
                val bundle = bundleOf("pinCode" to event.pin)
                Handler(Looper.getMainLooper()).postDelayed({
                    pinCodeViewModel.clearPinCode()
                }, 200)
                Handler(Looper.getMainLooper()).postDelayed({
                    checkPinAnim()
                }, 400)
                Handler(Looper.getMainLooper()).postDelayed({ setButtonsEnabled(true) },1000)
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.action_pinCodeFragment_to_confirmPinCodeFragment, bundle)
                }, 1200L)

                //                navigateWithDelay(NavGraphEvent.Main, 1000)
//                if (BiometricUtils.isBiometricReady(requireContext())) {
//                    findNavController().navigate(R.id.action_confirmPinCodeFragment_to_biometricPermissionFragment)
//                } else {
//                    pinCodeViewModel.setBiometrics(false)
////                    navigateWithDelay(NavGraphEvent.Main, 1000) buni coinfirm pin code ga
//                }

            }

            PinCodeEvent.PinValidated -> {
                setButtonsEnabled(false)
                Handler(Looper.getMainLooper()).postDelayed({
                    pinCodeViewModel.clearPinCode()
                }, 200)
                Handler(Looper.getMainLooper()).postDelayed({
                    checkPinAnim()
                }, 400)
                Handler(Looper.getMainLooper()).postDelayed({ setButtonsEnabled(true) },1000)
                navigateWithDelay(NavGraphEvent.Main, 1200)
            }

            PinCodeEvent.PinValidationFailed -> {
                setButtonsEnabled(false)
                Handler(Looper.getMainLooper()).postDelayed({
                    pinCodeViewModel.clearPinCode()
                }, 200)
                Handler(Looper.getMainLooper()).postDelayed({
                    checkPinAnim()
                }, 400)
                Handler(Looper.getMainLooper()).postDelayed({
                    errorPinAnim()
                }, 1000)
                Handler(Looper.getMainLooper()).postDelayed({
                    pinCodeViewModel.clearPinCode()
                    setButtonsEnabled(true)
                    binding.errorAttempts.visible()
                }, 2000)
            }
        }
    }

    private fun updatePinCode(pinCode: MutableList<String>) {
        with(binding) {
            val pinViews = listOf(view01, view02, view03, view04)
            pinViews.forEachIndexed { index, view ->
                view.setBackgroundResource(if (index < pinCode.size) R.drawable.bg_pin_active else R.drawable.bg_pin_inactive)
            }

//            if (pinCode.size == 4) {
//                if (pinCodeViewModel.fromRegister.value == true) {
//
//                }
//            }
        }
    }

    private fun navigateWithDelay(event: NavGraphEvent, delay: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            sharedVM.setNavGraphEvent(event)
        }, delay)
    }

    private fun checkPinAnim() {
        val pinViews = listOf(binding.view01, binding.view02, binding.view03, binding.view04)
        pinViews.forEachIndexed { index, view ->
            Handler(Looper.getMainLooper()).postDelayed({
                view.setBackgroundResource(R.drawable.bg_pin_active)
            }, index * 100L)
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
            this,
            Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    navigateWithDelay(NavGraphEvent.Main, 0)
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_prompt_title))
            .setSubtitle(getString(R.string.biometric_prompt_subtitle))
            .setNegativeButtonText(getString(R.string.biometric_prompt_cancel))
            .build()

        biometricPrompt.authenticate(promptInfo)
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
