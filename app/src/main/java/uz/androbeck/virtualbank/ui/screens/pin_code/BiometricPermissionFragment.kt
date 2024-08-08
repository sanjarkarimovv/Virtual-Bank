package uz.androbeck.virtualbank.ui.screens.pin_code

import android.os.Handler
import android.os.Looper
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentBiometricPermissionBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import java.util.concurrent.Executors

@AndroidEntryPoint
class BiometricPermissionFragment : BaseFragment(R.layout.fragment_biometric_permission) {

    private val binding by viewBinding(FragmentBiometricPermissionBinding::bind)
    private val viewModel: BiometricPermissionViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()
    override fun setup() {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.actionSkip.setOnClickListener {
            viewModel.setBiometrics(false)
            navigateWithDelay(NavGraphEvent.Main)
        }
        binding.turnOn.setOnClickListener {
            promptBiometricAuthentication()
        }
    }

    private fun promptBiometricAuthentication() {
        val biometricPrompt = BiometricPrompt(this, Executors.newSingleThreadExecutor(), object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                viewModel.setBiometrics(true)
                navigateWithDelay(NavGraphEvent.Main)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                viewModel.setBiometrics(false)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                viewModel.setBiometrics(false)
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_prompt_title))
            .setSubtitle(getString(R.string.biometric_prompt_subtitle))
            .setNegativeButtonText(getString(R.string.biometric_prompt_cancel))
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun navigateWithDelay(event: NavGraphEvent) {
        Handler(Looper.getMainLooper()).postDelayed({
            sharedVM.setNavGraphEvent(event)
        }, 500L)
    }
}