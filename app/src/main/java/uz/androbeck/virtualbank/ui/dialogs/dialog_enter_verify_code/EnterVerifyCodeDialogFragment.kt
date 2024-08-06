package uz.androbeck.virtualbank.ui.dialogs.dialog_enter_verify_code

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
import uz.androbeck.virtualbank.ui.screens.auth.registration.RegistrationFragment
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class EnterVerifyCodeDialogFragment : DialogFragment() {

    private val vm: EnterVerifyCodeDialogViewModel by viewModels()
    private var _binding: CustomVerifyDialogBinding? = null
    private val binding get() = _binding!!
    private var tokenForVerify: String? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenForVerify = arguments?.getString(RegistrationFragment.TOKEN_FOR_VERIFY)
        val editTextList = listOf(
            binding.ecet1,
            binding.ecet2,
            binding.ecet3,
            binding.ecet4,
            binding.ecet5,
            binding.ecet6,
        )

        vm.timerFinished.observe(viewLifecycleOwner) {
            if (it) {
                binding.llRefreshTimer.visibility = View.VISIBLE
                binding.llTimer.visibility = View.GONE
            } else {
                binding.llRefreshTimer.visibility = View.GONE
                binding.llTimer.visibility = View.VISIBLE
            }
        }
        vm.timerText.observe(viewLifecycleOwner) {
            binding.timer.text = it
        }

        vm.allFieldsFilled.observe(viewLifecycleOwner) {
            binding.confirmButton.isEnabled = it
        }

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
            vm.signUpVerify(codeStringBuilder.toString(), tokenForVerify)
            codeStringBuilder.clear()
        }
    }

    private fun observe() {
        vm.signUpVerifyEvent.onEach {
            if (it) {
                dismiss()
                onSuccessVerify?.invoke()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        messageController.observeMessage().onEach {
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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
}
