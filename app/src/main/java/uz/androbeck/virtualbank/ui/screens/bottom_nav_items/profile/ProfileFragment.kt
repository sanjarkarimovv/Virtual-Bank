package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()

    @Inject
    lateinit var preferencesProvider: PreferencesProvider
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
            val group = dialog?.findViewById<RadioGroup>(R.id.radio_gp)
            group?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    group.getChildAt(0).id -> {
                        vm.changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                        dialog?.dismiss()
                    }
                    group.getChildAt(1).id -> {
                        vm.changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                        dialog?.dismiss()
                    }

                    group.getChildAt(2).id -> {
                        vm.changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        dialog?.dismiss()
                    }
                }
            }
        }
    }
}