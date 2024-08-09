package uz.androbeck.virtualbank.ui.screens.Security

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSecurityBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible
import javax.inject.Inject

@AndroidEntryPoint
class SecurityFragment : BaseFragment(R.layout.fragment_security) {

    private lateinit var binding: FragmentSecurityBinding
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private val vm: SecurityViewModel by viewModels()

    @Inject
    lateinit var provider: PreferencesProvider
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSecurityBinding.inflate(inflater, container, false)
        seekBar = binding.sbAutoBlock
        textView = binding.tvAutoBlockTime
        return binding.root
    }


    override fun setup() = with(binding) {

        vm.seekBarResult(seekBar, textView, requireContext())


        biometricLogin.setOnCheckedChangeListener { _, isChecked ->
            provider.biometricLogin=isChecked }
        biometricLogin.isChecked = provider.biometricLogin


        paymentConfirmation.setOnCheckedChangeListener { _, isChecked ->
            provider.paymentConfirmation = isChecked }
        paymentConfirmation.isChecked = provider.paymentConfirmation


        automaticBlocking.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rlAutoBlock.gone()
            } else {
                rlAutoBlock.visible()
            }
            provider.autoBlockIsOn=isChecked
        }
        automaticBlocking.isChecked = provider.autoBlockIsOn


        usingGeolocation.setOnCheckedChangeListener { _, isChecked ->
            provider.useIsGeolocation = isChecked
        }
        usingGeolocation.isChecked = provider.useIsGeolocation
    }
}