package uz.androbeck.virtualbank.ui.customViews.dialogs.dialog_enter_verify_code

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import uz.androbeck.virtualbank.databinding.CustomVerifyDialogBinding

class CustomEnterVerifyCodeDialog : DialogFragment() {

    private val vm: CustomDialogViewModel by viewModels()
    private var _binding: CustomVerifyDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CustomVerifyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            }
            else{
                binding.llRefreshTimer.visibility = View.GONE
                binding.llTimer.visibility = View.VISIBLE
            }
        }
        vm.timerText.observe(viewLifecycleOwner) {
            binding.timer.text = it
        }

        vm.allFieldsFilled.observe(viewLifecycleOwner) {
            println("refresh button $it")
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
            editText.addTextChangedListener(object : TextWatcher {
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
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.white)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
