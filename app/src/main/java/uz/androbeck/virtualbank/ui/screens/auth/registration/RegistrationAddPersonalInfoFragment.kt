package uz.androbeck.virtualbank.ui.screens.auth.registration

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.Resources
import android.graphics.Rect
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentRegistrationAddPersonalInfoBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_BORN_DATE
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_FIRST_NAME
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_GENDER
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.USER_LAST_NAME
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegistrationAddPersonalInfoFragment :
    BaseFragment(R.layout.fragment_registration_add_personal_info) {

    private val binding by viewBinding(FragmentRegistrationAddPersonalInfoBinding::bind)
    private var genderValue = -1
    private var isKeyboardVisible = false

    override fun setup() {
        binding.btnNext.isEnabled = false
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content).viewTreeObserver.addOnGlobalLayoutListener(
            on
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun clicks() = with(binding) {
        btnBackSignIn.singleClickable {
            findNavController().popBackStack()
        }

        etFirstName.textInputEditText.addTextChangedListener {
            validateFields()
        }
        etLastName.textInputEditText.addTextChangedListener {
            validateFields()
        }
        etBornDate.addTextChangedListener {
            validateFields()
        }
        genderInput.addTextChangedListener {
            validateFields()
        }
        tilBornDate.setEndIconOnLongClickListener {
            showDatePickerDialog()
            true
        }
        val genderOptions = listOf(getString(R.string.str_male), getString(R.string.str_family))
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
                getString(R.string.str_family) -> 1
                else -> -1
            }
        }
        btnNext.singleClickable {
            val firstName = etFirstName.getText().trim()
            val lastName = etLastName.getText().trim()
            val bornDate = etBornDate.text.toString()
            val gender = genderValue
            val bundle = bundleOf(
                USER_FIRST_NAME to firstName,
                USER_LAST_NAME to lastName,
                USER_BORN_DATE to dateToMillis(bornDate),
                USER_GENDER to gender
            )
            findNavController().navigate(
                R.id.action_registrationAddPersonalInfoFragment_to_registrationFragment, bundle
            )
        }
        etBornDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                etBornDate.hint = getString(R.string.str_enter_born_date_format)
            } else {
                etBornDate.hint = Constants.String.EMPTY
            }
        }
        genderInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                genderInput.clearFocus()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                handleDateSelected(selectedYear, selectedMonth, selectedDay)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    @SuppressLint("DefaultLocale")
    private fun handleDateSelected(year: Int, month: Int, day: Int) {
        val formattedDate = String.format("%02d/%02d/%d", day, month + 1, year)
        binding.etBornDate.setText(formattedDate)
    }

    private fun validateFields() = with(binding) {
        val firstName = etFirstName.getText().trim()
        val lastName = etLastName.getText().trim()
        val bornDate = etBornDate.text.toString()
        val gender = genderInput.text.toString()
        btnNext.isEnabled =
            firstName.length >= 4 && lastName.length >= 4 && bornDate.length == 10 && gender.isNotEmpty()
    }

    private fun dateToMillis(dateString: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return "${date?.time ?: 0L}"
    }

    private val on = ViewTreeObserver.OnGlobalLayoutListener {
        if (view == null || !isAdded) return@OnGlobalLayoutListener

        val rect = Rect()
        val rootView =
            requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height
        val keypadHeight = screenHeight - rect.bottom

        val isKeyboardNowVisible = keypadHeight > screenHeight * 0.15

        if (isKeyboardNowVisible != isKeyboardVisible) {
            isKeyboardVisible = isKeyboardNowVisible
            onKeyboardVisibilityChanged(isKeyboardVisible, keypadHeight)
        }
    }


    private fun onKeyboardVisibilityChanged(visible: Boolean, keypadHeight: Int) {
        if (view == null || !isAdded) return

        val params = binding.ll.layoutParams as ViewGroup.MarginLayoutParams
        if (visible) {
            params.bottomMargin = keypadHeight + binding.ll.height
        } else {
            params.bottomMargin = 20.dpToPx()
        }
        binding.ll.layoutParams = params
    }

    private fun Int.dpToPx(): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (this * density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
            .viewTreeObserver.removeOnGlobalLayoutListener(on)
    }
}