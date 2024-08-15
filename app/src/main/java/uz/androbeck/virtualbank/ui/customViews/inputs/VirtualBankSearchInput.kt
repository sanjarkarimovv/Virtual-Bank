package uz.androbeck.virtualbank.ui.customViews.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomSearchEditTextBinding
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.pxToDp
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@SuppressLint("Recycle", "CustomViewStyleable")
class VirtualBankSearchInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        CustomSearchEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomSearchEditText)
            typedArray.recycle()
        }
        setupView()
    }

    private fun setupView() {
        with(binding) {
            customTvSearch.setOnFocusChangeListener { _, hasFocus ->
                val strokeWidthInDp =
                    if (hasFocus) Constants.Number.SELECT_CARD_STROKE_WIDTH else Constants.Number.DEFAULT_CARD_STROKE_WIDTH
                val strokeWidthInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    strokeWidthInDp.toFloat(),
                    resources.displayMetrics
                ).toInt()
                cvSearch.strokeWidth = strokeWidthInPx
                cvSearch.strokeColor = ContextCompat.getColor(
                    context,
                    if (hasFocus) R.color.colorPrimary else R.color.colorOutline
                )
            }
            customTvSearch.addTextChangedListener { s ->
                btnCancel.isVisible = s.toString().isNotEmpty()
            }
            btnCancel.singleClickable {
                customTvSearch.text.clear()
                customTvSearch.clearFocus()
            }
        }
    }

    fun getSearchTxt(): String {
        return binding.customTvSearch.text.toString()
    }
}