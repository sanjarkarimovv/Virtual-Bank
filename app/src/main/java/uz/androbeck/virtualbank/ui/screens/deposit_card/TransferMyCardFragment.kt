package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentTransferMyCardBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.colorStateList

class TransferMyCardFragment : BaseFragment(R.layout.fragment_transfer_my_card) {
    private val binding: FragmentTransferMyCardBinding by viewBinding()
    @SuppressLint("SetTextI18n")
    override fun setup() = with(binding) {
        cvTransferCard.setOnClickListener {

            CardsDialog{item->
                tvCardName.text=item.name
                tvCardBalance.text=item.amount.toString()
                tvCardNumber.text=item.pan
            }.show(childFragmentManager, "CardsDialog")
        }
        etInputTransferSum.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val inputNumber = s.toString().toIntOrNull() ?: 0
                if ((inputNumber in 0..999) || inputNumber > 15000000) {
                    etInputTransferSum.textInputLayout.hintTextColor=colorStateList(R.color.colorError)
                    tvTransferAmount.setTextColor(color(R.color.colorError))
                    etInputTransferSum.textInputLayout.boxStrokeColor  = color(R.color.colorError)
                    nextBtn.setOnClickListener {
//                        Next Translate Fragment
                    }
                } else {
                    etInputTransferSum.textInputLayout.hintTextColor=colorStateList(R.color.colorSecondary)
                    tvTransferAmount.setTextColor(color(R.color.colorSecondary))
                    etInputTransferSum.textInputLayout.boxStrokeColor = color(R.color.colorSecondary)
                }

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



}