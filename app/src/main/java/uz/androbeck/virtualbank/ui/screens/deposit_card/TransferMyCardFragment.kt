package uz.androbeck.virtualbank.ui.screens.deposit_card

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentTransferMyCardBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.color

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
        etInputTransferSum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val inputNumber = s.toString().toIntOrNull() ?: 0
                if ((inputNumber in 0..999) || inputNumber > 15000000) {
                    tvErrorInputTransferSum.visibility = View.VISIBLE
                    tvTransferAmount.setTextColor(color(R.color.colorError))
                    cvEtTransferSum.strokeColor = color(R.color.colorError)
                    cvEtNotEmpty.visibility = View.GONE
                    cvEtEmpty.visibility = View.VISIBLE
                } else {
                    tvTransferAmount.setTextColor(color(R.color.colorSecondary))
                    tvErrorInputTransferSum.visibility = View.GONE
                    cvEtTransferSum.strokeColor = color(R.color.colorSecondary)
                    cvEtNotEmpty.visibility = View.VISIBLE
                    cvEtEmpty.visibility = View.GONE
                }
                if (inputNumber.toString().isNotEmpty()){
                    ivCancelTransferSum.visibility = View.VISIBLE
                }
                else {
                    ivCancelTransferSum.visibility = View.GONE
                }
            }
        })
        ivCancelTransferSum.setOnClickListener {
            etInputTransferSum.text.clear()
            ivCancelTransferSum.visibility = View.GONE
            cvEtNotEmpty.visibility = View.GONE
            cvEtEmpty.visibility = View.VISIBLE
        }
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
        cvEtNotEmpty.setOnClickListener {
//            Kerakli transfer oynasiga o'tish uchun
        }

    }



}