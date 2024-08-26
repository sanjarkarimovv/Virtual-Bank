package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqUIModel
import uz.androbeck.virtualbank.databinding.FragmentUpdateInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class UpdateInfoFragment : BaseFragment(R.layout.fragment_update_info) {
    private val binding by viewBinding(FragmentUpdateInfoBinding::bind)
    private val vm by viewModels<UpdateFullInfoViewModel>()
    private var uiModel: FullInfoUIModel? = null
    private var genderValue = -1

    @Inject
    lateinit var prefsProvider: PreferencesProvider

    override fun setup(): Unit = with(binding) {
        getBundleData()
        setBundleDataToViews()
        listenChanges()
        genderChangerProgress()
    }

    override fun clicks() = with(binding) {
        toolbar.onClickLeftIcon = {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        btnDatePick.setOnClickListener {
            showCustomScrollableDialog()
        }
        genderInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                genderInput.clearFocus()
            }
        }
        btnSaveChanges.singleClickable {
            if (etDate.text?.length!! == 10) {
                val name = etName.text.toString()
                val lastname = etLastName.text.toString()
                val date = updatedDate()
                val gender = updatedGender()
                updateUserInformations(name, lastname, date, gender)
            } else {
                toast(getString(R.string.str_date_is_wrong))
            }
        }
    }

    private fun updatedGender(): String {
        val updatedGender: String
        binding.run {
            updatedGender = if (genderInput.text.toString() == getString(R.string.str_male)) {
                "0"
            } else {
                "1"
            }
        }
        return updatedGender
    }

    private fun updatedDate(): String {
        val milliseconds: String
        binding.run {
            milliseconds = dateToMillis(etDate.text.toString())
        }
        return milliseconds
    }

    @SuppressLint("SetTextI18n")
    private fun updateUserInformations(
        firstName: String,
        lastName: String,
        bornDate: String,
        gender: String
    ) = with(binding) {
        binding.btnSaveChanges.isEnabled = false
        viewLifecycleOwner.lifecycleScope.launch {
            vm.updateUserInfo(
                UpdateInfoReqUIModel(
                    firstName = firstName,
                    lastName = lastName,
                    bornDate = bornDate,
                    gender = gender
                )
            ).collect { event ->
                when (event) {
                    is UpdateFullInfoEvent.Error -> {
                        btnSaveChanges.text = getString(R.string.str_save_changes)
                        progressBar.gone()
                        toast(event.error)
                    }

                    UpdateFullInfoEvent.Loading -> {
                        println("::: -> Loading")
                        btnSaveChanges.text = ""
                        progressBar.visible()
                    }

                    is UpdateFullInfoEvent.Success -> {
                        println("::: -> Success")
                        btnSaveChanges.text = getString(R.string.str_save_changes)
                        toast(getString(R.string.str_updated_information_success))
                        progressBar.visibility = View.GONE
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun genderChangerProgress() = with(binding) {
        val genderOptions = listOf(getString(R.string.str_male), getString(R.string.str_female))
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_dropdown_item_1line, genderOptions
        )
        genderInput.setAdapter(adapter)
        genderInput.setOnTouchListener { _, _ ->
            genderInput.showDropDown()
            true
        }
        genderInput.setOnItemClickListener { _, _, position, _ ->
            val selectedGender = genderOptions[position]
            genderValue = when (selectedGender) {
                getString(R.string.str_male) -> 0
                getString(R.string.str_female) -> 1
                else -> -1
            }
            when (position) {
                0 -> genderImage.setImageResource(R.drawable.avatar_male)
                1 -> genderImage.setImageResource(R.drawable.avatar_female)
            }
        }
    }

    private fun listenChanges() = with(binding) {
        var isNameEqual: Boolean
        var isLastNameEqual: Boolean
        val name = uiModel?.firstName
        val lastName = uiModel?.lastName
        binding.etName.addTextChangedListener {
            isNameEqual = it.toString() == name
            btnSaveChanges.isEnabled = !isNameEqual
        }
        binding.etLastName.addTextChangedListener {
            isLastNameEqual = it.toString() == lastName
            btnSaveChanges.isEnabled = !isLastNameEqual
        }
        etDate.addTextChangedListener {
            btnSaveChanges.isEnabled = it.toString() != prefsProvider.dateOFBirth
        }
        genderInput.addTextChangedListener {
            if (uiModel?.gender?.toInt() == 0) {
                btnSaveChanges.isEnabled = it.toString() != getString(R.string.str_male)
            } else {
                btnSaveChanges.isEnabled = it.toString() != getString(R.string.str_female)
            }
        }
    }

    private fun showCustomScrollableDialog() = with(binding) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.custom_scrollable_date_picker_dialog)
            .setCancelable(true)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        val selectButton = dialog.findViewById<MaterialButton>(R.id.btn_select)
        val datePicker = dialog.findViewById<DatePicker>(R.id.scrollable_date_picker)
        selectButton?.setOnClickListener {
            val day = datePicker?.dayOfMonth
            val month = datePicker?.month?.plus(1)
            val year = datePicker?.year
            val dayStr = if (day!! < 10) "0$day" else day.toString()
            val monthStr = if (month!! < 10) "0$month" else month.toString()
            val formattedDate = "$dayStr$monthStr$year"
            etDate.setText(formattedDate)
            dialog.dismiss()
        }
    }

    private fun setBundleDataToViews() = with(binding) {
        uiModel?.let {
            uiModel?.run {
                etName.setText(firstName)
                etLastName.setText(lastName)
                etDate.setText(bornDate?.let { millisecond -> millisToDate(millisecond.toLong() - 86_400_000L) })
                phoneNumberReceiver.text = phone.toString()
                if (gender?.toInt() == 0) {
                    genderInput.setText(getString(R.string.str_male))
                    genderImage.setImageResource(R.drawable.avatar_male)
                } else {
                    genderImage.setImageResource(R.drawable.avatar_female)
                    genderInput.setText(getString(R.string.str_female))
                }
            }
        }
        prefsProvider.dateOFBirth = binding.etDate.text.toString()
    }

    private fun getBundleData() {
        uiModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(
                Constants.ArgumentKey.USER_FULL_INFO, FullInfoUIModel::class.java
            )
        } else {
            arguments?.getSerializable(Constants.ArgumentKey.USER_FULL_INFO) as? FullInfoUIModel
        }
        println(uiModel)
    }

    private fun dateToMillis(dateString: String): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return date?.time.toString()
    }

    private fun millisToDate(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = Date(milliseconds)
        return dateFormat.format(date)
    }

    override fun onDestroy() {
        prefsProvider.dateOFBirth = ""
        super.onDestroy()
    }
}