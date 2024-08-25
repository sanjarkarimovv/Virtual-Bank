package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import android.annotation.SuppressLint
import android.os.Build
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentUpdateInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.dpToPx
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
            if (it.toString() == getString(R.string.str_male) && uiModel?.gender?.toInt() == 1) {
                println("::: it -> $it || uiModel?.gender?.toInt() -> ${uiModel?.gender?.toInt()}")
                btnSaveChanges.isEnabled = false
            } else if (it.toString() == getString(R.string.str_male) && uiModel?.gender?.toInt() == 0){
                println("::: it -> $it || uiModel?.gender?.toInt() -> ${uiModel?.gender?.toInt()}")
                btnSaveChanges.isEnabled = false
            }
        }
    }

    private fun showCustomScrollableDialog() = with(binding) {
        val layoutParams = WindowManager.LayoutParams()
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.custom_scrollable_date_picker_dialog)
            .setCancelable(true)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        layoutParams.copyFrom(dialog.window?.attributes)

        layoutParams.height = (dpToPx(550))
        val selectButton = dialog.findViewById<MaterialButton>(R.id.btn_select)
        val datePicker = dialog.findViewById<DatePicker>(R.id.scrollable_date_picker)
        selectButton?.setOnClickListener {
            val day = datePicker?.dayOfMonth
            val month = datePicker?.month?.plus(1) // Add 1 to get the actual month number
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
                if (gender?.toInt() == 1) {
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


    private fun dateToMillis(dateString: String): Long {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return date?.time ?: 0
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