package uz.androbeck.virtualbank.ui.screens.auth.registration

import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentRegistrationBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.events.NavGraphEvent
import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val sharedVM: MainSharedViewModel by activityViewModels()

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    override fun setup() {
        binding.btnRegister.setOnClickListener {
            prefsProvider.token = "Bearer token"
            sharedVM.setNavGraphEvent(NavGraphEvent.Main)
        }
    }
}