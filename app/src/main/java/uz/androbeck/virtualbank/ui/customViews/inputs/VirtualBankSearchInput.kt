package uz.androbeck.virtualbank.ui.customViews.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomSearchEditTextBinding
import uz.androbeck.virtualbank.utils.extentions.pxToDp
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@SuppressLint("Recycle")
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
                if (hasFocus) {
                    cvSearch.strokeWidth = pxToDp(2)
                    cvSearch.strokeColor = ContextCompat.getColor(context, R.color.screenTextColor)
                } else {
                    cvSearch.strokeWidth = pxToDp(1)
                    cvSearch.strokeColor = ContextCompat.getColor(context, R.color.colorOutline)
                }
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