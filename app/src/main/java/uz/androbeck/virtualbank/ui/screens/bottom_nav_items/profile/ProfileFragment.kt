package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogWidgetSetupBinding
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.ProfileViewModel.Companion.nightMode
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    private var dialog: BottomSheetDialog? = null
    override fun setup() {
        vm.fullInfoEvent.onEach { fullInfo ->
            val user = fullInfo.firstName + " " + fullInfo.lastName
            binding.user.text = user
        }
        binding.widgetSetup.singleClickable {
            if (dialog == null) {
                dialog = BottomSheetDialog(requireContext(), R.style.Transparent)
            }
            dialog?.run {
                setContentView(R.layout.dialog_widget_setup)
                setCancelable(true)
                setCanceledOnTouchOutside(true)
                show()
            }
            nightMode(dialog!!)
        }
    }
}