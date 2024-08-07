package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.annotation.SuppressLint
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

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


    }
}