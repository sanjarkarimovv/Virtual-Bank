package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security

import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSecurityBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.enums.SecuritySettingKey
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.models.SecuritySettings
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class SecurityFragment :BaseFragment(R.layout.fragment_security) {
    private val binding by viewBinding(FragmentSecurityBinding::bind)
    private val viewModel by viewModels<SecurityViewModel>()
    override fun setup() {
    }

    override fun observe() {
        viewModel.apply {
            securitySettings.observe(viewLifecycleOwner) {
                updateUI(it)
            }
        }
    }

    override fun clicks() = with(binding) {
        securityToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_securityFragment_to_profileFragment)
        }

        changePin.singleClickable{
            findNavController().navigate(R.id.action_securityFragment_to_changePinCodeFragment)
        }

        seekBarTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                selectedTimeText.text = requireContext().getString(R.string.str_security_time, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.updateSetting(SecuritySettingKey.AWAY_LONG_TIME, seekBar?.progress ?: 0)
            }
        })

        biometricSettings.singleClickable{
            switchBiometrics.isChecked = !switchBiometrics.isChecked
            viewModel.updateSetting(SecuritySettingKey.USE_BIOMETRIC, switchBiometrics.isChecked)
        }

        switchBiometrics.setOnCheckedChangeListener{
                _, isChecked ->
            viewModel.updateSetting(SecuritySettingKey.USE_BIOMETRIC, isChecked)
        }

        paymentSettings.singleClickable{
            switchPayment.isChecked = !switchPayment.isChecked
            viewModel.updateSetting(SecuritySettingKey.USE_BIOMETRIC_PAYMENT, switchPayment.isChecked)
        }

        switchPayment.setOnCheckedChangeListener{
                _, isChecked ->
            viewModel.updateSetting(SecuritySettingKey.USE_BIOMETRIC_PAYMENT, isChecked)
        }

        lockSettings.singleClickable{
            switchLock.isChecked = !switchLock.isChecked
            viewModel.updateSetting(SecuritySettingKey.USE_IS_AWAY_LONG, switchLock.isChecked)
        }

        switchLock.setOnCheckedChangeListener{
                _, isChecked ->
            viewModel.updateSetting(SecuritySettingKey.USE_IS_AWAY_LONG, isChecked)
        }
    }

    private fun updateUI(settings: SecuritySettings) = with(binding) {
        // Disable animations temporarily
        switchBiometrics.jumpDrawablesToCurrentState()
        switchPayment.jumpDrawablesToCurrentState()
        switchLock.jumpDrawablesToCurrentState()

        // Apply settings without animation
        settings.run {
            switchBiometrics.isChecked = useBiometric
            switchPayment.isChecked = useBiometricPayment
            switchLock.isChecked = useIsAwayLong
            seekBarTime.progress = awayLongTime
            selectedTimeText.text = requireContext().getString(R.string.str_security_time, awayLongTime)
        }
    }

}