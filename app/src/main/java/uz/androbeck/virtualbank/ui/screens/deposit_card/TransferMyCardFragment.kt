@file:Suppress("DEPRECATION")

package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentTransferMyCardBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.colorStateList

class TransferMyCardFragment : BaseFragment(R.layout.fragment_transfer_my_card) {
    private val binding: FragmentTransferMyCardBinding by viewBinding()
    @SuppressLint("SetTextI18n")
    override fun setup() = with(binding) {
        val cardUiModel = arguments?.getParcelable<CardUIModel>("card")
        tvCardName.text=cardUiModel?.name
        tvCardBalance.text=cardUiModel?.amount.toString()
        tvCardNumber.text=cardUiModel?.pan
        cvTransferCard.setOnClickListener {
            CardsDialog{item->
                tvCardName.text=item.name
                tvCardBalance.text=item.amount.toString()
                tvCardNumber.text=item.pan
            }.show(childFragmentManager, "CardsDialog")
        }
        etInputTransferSum.textInputEditText.addTextChangedListener(object : TextWatcher {
            private var isFormatting: Boolean = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return

                isFormatting = true

                val formattedText = s.toString().replace(" ", "")
                val formattedWithSpaces = StringBuilder()
                for (i in formattedText.indices) {
                    formattedWithSpaces.append(formattedText[i])
                    if ((i + 1) % 3 == 0 && i != formattedText.length - 1) {
                        formattedWithSpaces.append(" ")
                    }
                }

                if (formattedWithSpaces.toString() != s.toString()) {
                    etInputTransferSum.textInputEditText.setText(formattedWithSpaces.toString())
                    etInputTransferSum.textInputEditText.setSelection(formattedWithSpaces.length)
                }

                val inputNumber = formattedWithSpaces.toString().replace(" ", "").toIntOrNull() ?: 0
                if ((inputNumber in 0..999) || inputNumber > 15000000) {
                    etInputTransferSum.textInputLayout.hintTextColor = colorStateList(R.color.colorError)
                    tvTransferAmount.setTextColor(color(R.color.colorError))
                    etInputTransferSum.textInputLayout.boxStrokeColor = color(R.color.colorError)
                    nextBtn.setOnClickListener {
                        // Next Translate Fragment
                    }
                } else {
                    etInputTransferSum.textInputLayout.hintTextColor = colorStateList(R.color.colorSecondary)
                    tvTransferAmount.setTextColor(color(R.color.colorSecondary))
                    etInputTransferSum.textInputLayout.boxStrokeColor = color(R.color.colorSecondary)
                }

                isFormatting = false
            }
        })
        toolbar.onClickLeftIcon={
            findNavController().popBackStack()
        }
        cvTransfer50.setOnClickListener {
            etInputTransferSum.setText("50000")
        }
        cvTransfer100.setOnClickListener {
            etInputTransferSum.setText("100000")
        }
        cvTransfer200.setOnClickListener{
            etInputTransferSum.setText("200000")
        }
        cvTransfer500.setOnClickListener {
            etInputTransferSum.setText("500000")
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("selected_position", -1)
        editor.remove("shared")
        editor.apply()
    }
}