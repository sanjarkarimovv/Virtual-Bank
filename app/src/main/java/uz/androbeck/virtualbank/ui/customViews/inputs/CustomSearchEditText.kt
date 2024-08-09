package uz.androbeck.virtualbank.ui.customViews.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomSearchEditTextBinding

@SuppressLint("Recycle")
class CustomSearchEditText @JvmOverloads constructor(
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
                    val strokeWidthInDp = 2
                    val strokeWidthInPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        strokeWidthInDp.toFloat(),
                        resources.displayMetrics
                    ).toInt()
                    cvSearch.strokeWidth = strokeWidthInPx
                    cvSearch.strokeColor = ContextCompat.getColor(context, R.color.colorPrimary)
                } else {
                    val strokeWidthInDp = 1
                    val strokeWidthInPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        strokeWidthInDp.toFloat(),
                        resources.displayMetrics
                    ).toInt()
                    cvSearch.strokeWidth = strokeWidthInPx
                    cvSearch.strokeColor = ContextCompat.getColor(context, R.color.colorOutline)
                }
            }
            customTvSearch.addTextChangedListener { s ->
                if (s.toString().isNotEmpty()) {
                    btnCancel.visibility = VISIBLE
                } else {
                    btnCancel.visibility = GONE
                }
            }
            btnCancel.setOnClickListener {
                customTvSearch.text.clear()
                customTvSearch.clearFocus()
            }
        }
    }

    fun getSearchTxt(): String {
        return binding.customTvSearch.text.toString()
    }

}