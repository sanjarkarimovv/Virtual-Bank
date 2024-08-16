package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security

import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSecurityBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.enums.SecuritySettingKey
import javax.inject.Inject

@AndroidEntryPoint
class SecurityFragment :BaseFragment(R.layout.fragment_security) {
    private val binding by viewBinding(FragmentSecurityBinding::bind)
    private val viewModel by viewModels<SecurityViewModel>()
    override fun setup() {

    }

    private fun updateUI() = with(binding) {
        switchBiometrics.isChecked
    }

    override fun observe() {
        viewModel.apply {
            securitySettings.observe(viewLifecycleOwner) {
                it.run {
                    with(binding) {
                        switchBiometrics.isChecked = useBiometric
                        switchPayment.isChecked = useBiometricPayment
                        switchLock.isChecked = useIsAwayLong
                        seekBarTime.progress = awayLongTime
                        selectedTimeText.text = requireContext().getString(R.string.str_security_time, awayLongTime)
                    }
                }
            }
        }
    }

    override fun clicks() = with(binding) {
        securityToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_securityFragment_to_profileFragment)
        }
        seekBarTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                selectedTimeText.text = requireContext().getString(R.string.str_security_time, progress)
                viewModel.updateSetting(SecuritySettingKey.AWAY_LONG_TIME, progress)
                println(progress
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

}