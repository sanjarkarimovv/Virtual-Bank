package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import android.app.DatePickerDialog
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqUIModel
import uz.androbeck.virtualbank.databinding.FragmentUpdateInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    override fun clicks() = with(binding) {
        btnUndo.setOnClickListener {
            changingButtonsMakeInvisible()
        }
        btnChangeDate.setOnClickListener {
            datePicker()
        }
        btnChangeGender.setOnClickListener {
            if (genderInfoReceiver.text == "Male") {
                genderInfoReceiver.text = getString(R.string.str_female)
            } else {
                genderInfoReceiver.text = getString(R.string.str_male)
            }
        }
        btnSaverChanges.setOnClickListener {
            println("::: -> btn changes clicked")
//            updateChanges()
        }
    }

    private fun updateChanges() = with(binding) {
        println("::: -> update changes")
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val date = dateBirthReceiver.text.toString()
        var gender: String? = null
        if (genderInfoReceiver.text == getString(R.string.str_male)) {
            gender = "1"
        } else if (genderInfoReceiver.text == getString(R.string.str_male)) {
            gender = "0"
        }
        val reqDate = dateToMillis(date)
        viewLifecycleOwner.lifecycleScope.launch {
            vm.updateUserInfo(
                UpdateInfoReqUIModel(
                    firstName = firstName,
                    lastName = lastName,
                    bornDate = millisToDate(reqDate),
                    gender = gender
                )
            ).collect { event ->
                when (event) {
                    is UpdateFullInfoEvent.Error -> {
                        println("Error buttons make invisible !")
                        toast(event.error)
                        btnSaverChanges.text = getString(R.string.str_save_changes)
                        changingButtonsMakeInvisible()
                    }

                    UpdateFullInfoEvent.Loading -> {
                        changingButtonsMakeInvisible()
                        btnSaverChanges.text = null
                    }

                    is UpdateFullInfoEvent.Success -> {
                        println("Success buttons make invisible !")
                        changingButtonsMakeInvisible()
                        btnSaverChanges.text = getString(R.string.str_save_changes)
                    }
                }
            }
        }
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

    private fun changingButtonsMakeVisible() = with(binding) {
        icDateIv.gone()
        updateButtonsRoot.visible()
        btnChangeDate.visible()
        btnChangeGender.visible()
    }

    private fun datePicker() = with(binding) {
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
        icDateIv.visible(   )
        updateButtonsRoot.gone()
        btnChangeDate.gone()
        btnChangeGender.gone()
    }

    private fun dateToMillis(dateString: String): Long {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return date?.time ?: 0
    }

    private fun millisToDate(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = Date(milliseconds)
        return dateFormat.format(date)
    }
}