package uz.androbeck.virtualbank.ui.dialogs.enter_verify_code

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.databinding.CustomVerifyDialogBinding
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.screens.Screen
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.PHONE_NUMBER_FOR_VERIFY
import uz.androbeck.virtualbank.utils.Constants.ArgumentKey.TOKEN_FOR_VERIFY
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class EnterVerifyCodeDialogFragment : DialogFragment() {

    private val vm: EnterVerifyCodeDialogViewModel by viewModels()
    private var _binding: CustomVerifyDialogBinding? = null
    private val binding get() = _binding!!
    private var tokenForVerify: String? = null
    private var phoneNumber: String? = null
    private var screen: Screen? = null

    @Inject
    lateinit var messageController: MessageController

    var onSuccessVerify: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CustomVerifyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        tokenForVerify = arguments?.getString(TOKEN_FOR_VERIFY)
        phoneNumber = arguments?.getString(PHONE_NUMBER_FOR_VERIFY)
        arguments?.getString(Constants.ArgumentKey.SCREEN)?.let {
            screen = Screen.valueOf(it)
        }
        val editTextList = listOf(
            ecet1,
            ecet2,
            ecet3,
            ecet4,
            ecet5,
            ecet6,
        )

        tvPhoneNumber.text = formatPhoneNumber(phoneNumber.toString())

        for (i in editTextList.indices) {
            if (i < editTextList.size - 1) {
                editTextList[i].nextEditText = editTextList[i + 1]
            }
            if (i > 0) {
                editTextList[i].previousEditText = editTextList[i - 1]
            }
        }

        editTextList.forEachIndexed { index, editText ->
            editText.addTextChangedListeners(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editText.nextEditText?.requestFocus()
                    } else if (s?.length == 0) {
                        editText.previousEditText?.requestFocus()
                    }
                    vm.updateEditTextValue(index, s.toString())
                }
            })
        }
        observe()
        clicks()
    }


    private val codeStringBuilder = StringBuilder()
    private fun clicks() = with(binding) {
        confirmButton.singleClickable {
            vm.editTextValues.value?.forEach {
                codeStringBuilder.append(it)
            }
            vm.authVerify(codeStringBuilder.toString(), tokenForVerify, screen)
            codeStringBuilder.clear()
        }
    }

    private fun observe() = with(binding) {
        vm.signUpVerifyEvent.onEach {
            if (it) {
                dismiss()
                onSuccessVerify?.invoke()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        messageController.observeMessage().onEach {
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        vm.isError.observe(viewLifecycleOwner) {
            if (it) {
                reEnterCode()
            }
        }

        vm.timerFinished.observe(viewLifecycleOwner) {
            if (it) {
                llRefreshTimer.visibility = View.VISIBLE
                llTimer.visibility = View.GONE
            } else {
                llRefreshTimer.visibility = View.GONE
                llTimer.visibility = View.VISIBLE
            }
        }
        vm.timerText.observe(viewLifecycleOwner) {
            timer.text = it
        }

        vm.allFieldsFilled.observe(viewLifecycleOwner) {
            binding.confirmButton.isEnabled = it
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.white)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun reEnterCode() {
        with(binding) {
            ecet1.setText(Constants.String.EMPTY)
            ecet2.setText(Constants.String.EMPTY)
            ecet3.setText(Constants.String.EMPTY)
            ecet4.setText(Constants.String.EMPTY)
            ecet5.setText(Constants.String.EMPTY)
            ecet6.setText(Constants.String.EMPTY)
            ecet1.requestFocus()
            confirmButton.isEnabled = false
        }
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        val cleaned = phoneNumber.filter { it.isDigit() }
        val countryCode = cleaned.substring(0, 3)
        val operatorCode = cleaned.substring(3, 5)
        val lastTwoDigits = cleaned.takeLast(2)
        return "+$countryCode $operatorCode *** ** $lastTwoDigits"
    }
}
