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
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogWidgetSetupBinding
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    private val dialog by lazy { BottomSheetDialog(requireContext(), R.style.Transparent) }
    private val dialogBinding by viewBinding(DialogWidgetSetupBinding::bind)

    override fun setup() {
        vm.fullInfoEvent.onEach { fullInfo ->
            val user = fullInfo.firstName + " " + fullInfo.lastName
            binding.user.text = user
        }
        binding.widgetSetup.singleClickable {
            val window = dialog.window
            window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog.run {
                setContentView(R.layout.dialog_widget_setup)
                setCancelable(true)
                setCanceledOnTouchOutside(true)
                show()
            }
            val group = dialog.findViewById<RadioGroup>(R.id.radio_gp)
            group?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    group.getChildAt(0).id -> {
                        Toast.makeText(requireContext(), "Light Mode", Toast.LENGTH_SHORT).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            dialog.dismiss()
                        }, 500)
                    }

                    group.getChildAt(1).id -> {
                        Toast.makeText(requireContext(), "Dark Mode", Toast.LENGTH_SHORT).show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            dialog.dismiss()
                        }, 500)
                    }

                    group.getChildAt(2).id -> {
                        Toast.makeText(requireContext(), "System Mode", Toast.LENGTH_SHORT).show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            dialog.dismiss()
                        }, 500)
                    }
                }
            }
        }
    }
}