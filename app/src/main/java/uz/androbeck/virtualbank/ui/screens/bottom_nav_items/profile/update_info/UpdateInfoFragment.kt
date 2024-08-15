@file:Suppress("DEPRECATION")

package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import android.app.DatePickerDialog
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentUpdateInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class UpdateInfoFragment : BaseFragment(R.layout.fragment_update_info) {
    private val binding by viewBinding(FragmentUpdateInfoBinding::bind)
    private val vm by viewModels<UpdateFullInfoViewModel>()
    private var uiModel: FullInfoUIModel? = null

    override fun setup(): Unit = with(binding) {
        getBundleData()
        onCreateMenu()
        listenings()
        setBundleDataToViews()
    }

    private fun setBundleDataToViews() = with(binding) {
        if (uiModel?.gender?.toInt() == 1) {
            genderInfoReceiver.text = getString(R.string.str_male)
        } else {
            genderInfoReceiver.text = getString(R.string.str_female)
        }
        etFirstName.setText(uiModel?.firstName)
        etLastName.setText(uiModel?.lastName)
        dateBirthReceiver.text = uiModel?.bornDate
    }

    private fun getBundleData() {
        uiModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(
                Constants.ArgumentKey.USER_FULL_INFO,
                FullInfoUIModel::class.java
            )
        } else {
            arguments?.getSerializable(Constants.ArgumentKey.USER_FULL_INFO) as? FullInfoUIModel
        }
        println(uiModel)
    }

    private fun onCreateMenu() = with(binding) {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_change_info_option -> {
                    changingButtonsMakeVisible()
                    true
                }

                else -> false
            }
        }
    }

    private fun listenings() = with(binding) {
        etFirstName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                helperFirstNameTitle.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                helperFirstNameTitle.textSize = 16f
            } else {
                helperFirstNameTitle.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorInverseSurface
                    )
                )
                helperFirstNameTitle.textSize = 14f
            }
        }
        etLastName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                helperLastNameTitle.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                helperLastNameTitle.textSize = 16f
            } else {
                helperLastNameTitle.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorInverseSurface
                    )
                )
                helperLastNameTitle.textSize = 14f
            }
        }
    }

    override fun clicks() = with(binding) {
        btnUndo.setOnClickListener {
            changingButtonsMakeInvisible()
        }
        btnDateAndBirthChanger.setOnClickListener {
            datePicker()
        }
        btnGenderChanger.setOnClickListener {
            if (genderInfoReceiver.text == "Male") {
                genderInfoReceiver.text = getString(R.string.str_female)
            } else {
                genderInfoReceiver.text = getString(R.string.str_male)
            }
        }
        btnSaverChanges.setOnClickListener {

        }
    }

    private fun changingButtonsMakeVisible() = with(binding) {
        btnSaverChanges.isEnabled = true
        btnSaverChanges.isEnabled = true
        btnSaverChanges.strokeWidth = 1
        btnSaverChanges.setTextColor(ContextCompat.getColor(requireContext(), R.color.static_white))
        btnUndo.visible()
        btnDateAndBirthChanger.visible()
        btnGenderChanger.visible()
        tvPhoneNumber.gone()
        tvDeleteAccount.gone()
    }

    private fun datePicker() = with(binding) {
//        val oldDate = dateBirthReceiver.text.split(".")
//        val defYear = oldDate[0]
//        val defMonth = oldDate[1]
//        val defDay = oldDate[2]
//        println("Year => $defYear Month -> $defMonth Day -> $defDay")
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedDate = "$year.${month + 1}.$dayOfMonth"
                dateBirthReceiver.text = selectedDate
            },
            2000,
            0,
            1
        )
        datePickerDialog.setCancelable(true)
        datePickerDialog.show()
    }

    private fun changingButtonsMakeInvisible() = with(binding) {
        etFirstName.isEnabled = false
        etLastName.isEnabled = false
        etFirstName.isFocusable = false
        etLastName.isFocusable = false
        btnSaverChanges.isEnabled = false
        btnSaverChanges.strokeWidth = 0
        btnUndo.gone()
        btnDateAndBirthChanger.gone()
        btnGenderChanger.gone()
        tvPhoneNumber.visible()
        tvDeleteAccount.visible()
    }
}