package uz.androbeck.virtualbank.ui.customViews.inputs

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ViewVirtualBankCardNumberInputBinding
import uz.androbeck.virtualbank.ui.enums.CardType
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.setTextColorRes
import uz.androbeck.virtualbank.utils.extentions.singleClickable

class VirtualBankCardNumberInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        ViewVirtualBankCardNumberInputBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        setupView()
    }

    private fun setupView() {
        with(binding) {
            metCardNumber.setOnFocusChangeListener { _, hasFocus ->
                val strokeWidthInDp = if (hasFocus) 2 else 1
                val strokeWidthInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    strokeWidthInDp.toFloat(),
                    resources.displayMetrics
                ).toInt()

                cvCard.strokeWidth = strokeWidthInPx
                cvCard.strokeColor = ContextCompat.getColor(
                    context,
                    if (hasFocus) R.color.colorPrimary else R.color.colorOutline
                )
                tvHelperText.setTextColorRes(if (hasFocus) R.color.colorPrimary else R.color.colorScrim)
            }
            metCardNumber.addTextChangedListener { s ->
                btnCancel.isVisible = s.toString().isNotEmpty()
                btnScanner.isVisible = s.toString().isEmpty()
                val str = s.toString().filter { it.isDigit() }
                val cardType = if (str.length >= 4) CardType.fromCardNumber(
                    str.substring(
                        0,
                        4
                    )
                ) else CardType.UNKNOWN

                when (cardType) {
                    CardType.UZCARD -> {
                        ivCard.setImageResource(R.drawable.img_uzcard_logo)
                    }

                    CardType.HUMO -> {
                        ivCard.setImageResource(R.drawable.img_humo_logo)
                    }

                    CardType.UNKNOWN -> {
                        ivCard.setImageResource(R.drawable.ic_cards)
                    }
                }
            }
            btnCancel.singleClickable {
                metCardNumber.text?.clear()
            }
        }
    }

    fun getCardNumber(): String {
        return binding.metCardNumber.text.toString()
    }

    fun addTextChangedListener(afterTextChanged: (Editable?) -> Unit) {
        binding.metCardNumber.addTextChangedListener(afterTextChanged = afterTextChanged)
    }

    fun addCardError() = with(binding) {
        val strokeWidthInDp = Constants.Number.SELECT_CARD_STROKE_WIDTH
        val strokeWidthInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            strokeWidthInDp.toFloat(),
            resources.displayMetrics
        ).toInt()
        cvCard.strokeWidth = strokeWidthInPx
        cvCard.strokeColor = ContextCompat.getColor(context, R.color.colorError)
        tvHelperText.setTextColorRes(R.color.colorError)
    }
}