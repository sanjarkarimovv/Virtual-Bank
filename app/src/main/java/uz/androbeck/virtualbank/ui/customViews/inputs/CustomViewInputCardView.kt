package uz.androbeck.virtualbank.ui.customViews.inputs

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.vicmikhailau.maskededittext.MaskedEditText
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomViewInputCardViewBinding
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.setTextColorRes
import uz.androbeck.virtualbank.utils.extentions.visible

class CustomViewInputCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        CustomViewInputCardViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var onClickEditText: () -> Unit = {}
    var maskedEditText: MaskedEditText
    var textView: TextView
    init {
        setUpView()
        maskedEditText = binding.metCardPhoneNumber
        textView = binding.tvTitleInputCard
        attrs?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomViewInputCardView)
            val hintText = typedArray.getString(R.styleable.CustomViewInputCardView_masHintText)
            val text = typedArray.getString(R.styleable.CustomViewInputCardView_editTextInCus)
            maskedEditText.hint = hintText
            textView.text = text
            typedArray.recycle()
        }
    }

    private fun setUpView() {
        with(binding) {
            metCardPhoneNumber.setOnClickListener {
            }
            metCardPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    onClickEditText()
                }
                val strokeWidthInDp = if (hasFocus) 2 else 1
                val strokeWidthInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    strokeWidthInDp.toFloat(),
                    resources.displayMetrics
                ).toInt()
                mcvCard.strokeWidth = strokeWidthInPx
                mcvCard.strokeColor = ContextCompat.getColor(
                    context,
                    if (hasFocus) R.color.colorPrimary else R.color.colorOutline
                )
                tvTitleInputCard.setTextColorRes(if (hasFocus) R.color.colorPrimary else R.color.colorOutline)
            }
            metCardPhoneNumber.addTextChangedListener { s ->
                if (s.toString().isNotEmpty()) {
                    ivScanCard.visible()
                    ivScanCard.isEnabled = true
                } else {
                    ivScanCard.gone()
                    ivScanCard.isEnabled = false
                }

            }
        }

        fun GetSumm(): String {
            return binding.metCardPhoneNumber.text.toString()
        }


    }
}

