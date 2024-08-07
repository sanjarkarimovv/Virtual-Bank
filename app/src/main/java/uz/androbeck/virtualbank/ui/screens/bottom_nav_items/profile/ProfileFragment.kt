package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.annotation.SuppressLint
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val vm by viewModels<ProfileViewModel>()
    override fun setup() {
        binding = FragmentProfileBinding.bind(requireView())
        binding.tvYazik.setOnClickListener {
            showBottomSheet()
        }
    }

    @SuppressLint("InflateParams")
    private fun showBottomSheet() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.item_boottom_sheet_language, null)
        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()




        vm.fullInfoEvent.onEach { fullInfo->
            val user=fullInfo.firstName+" "+fullInfo.lastName
            binding.user.text=user
        }


    }
}