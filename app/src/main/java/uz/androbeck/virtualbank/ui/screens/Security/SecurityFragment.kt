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
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentSecurityBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible
import javax.inject.Inject

@AndroidEntryPoint
class SecurityFragment : BaseFragment(R.layout.fragment_security) {

    private lateinit var binding: FragmentSecurityBinding
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private lateinit var locationManager: LocationManager
    lateinit var locationListener: LocationListener
    private val vm: SecurityViewModel by viewModels()
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels()

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
        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                provider.userLocation = location.toString()
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        biometricLogin.isChecked = provider.biometricLogin
        paymentConfirmation.isChecked = provider.paymentConfirmation
        usingGeolocation.isChecked = provider.useIsGeolocation
        automaticBlocking.isChecked = provider.autoBlockIsOn
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this) {
                mainSharedViewModel.setNavGraphEvent(NavGraphEvent.Main)

            }
    }

    override fun setup() = with(binding) {


        vm.seekBarResult(seekBar, textView, requireContext())

        biometricLogin.setOnCheckedChangeListener { _, isChecked ->
            vm.biometricLogin(isChecked)
        }
        paymentConfirmation.setOnCheckedChangeListener { _, isChecked ->
            vm.paymentConfirmation(isChecked)
        }
        automaticBlocking.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                rlAutoBlock.gone()
            }else{
                rlAutoBlock.visible()
            }
            vm.autoBlockIsOn(isChecked)
        }
        usingGeolocation.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                vm.startLocation(requireContext())
            } else {
                vm.stopLocation(requireContext())
            }
        }
    }
}