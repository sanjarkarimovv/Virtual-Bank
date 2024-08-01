package uz.android.signuptaskexample.custom_edit_text

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uz.androbeck.virtualbank.R

class Gender @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout
    private var textInputEditText: TextInputEditText

    init {
        LayoutInflater.from(context).inflate(R.layout.gender, this, true)

        textInputLayout = findViewById(R.id.gender_layout)
        textInputEditText = findViewById(R.id.gender_edit_text)

        attrs?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomTextField)
            val hintText = typedArray.getString(R.styleable.CustomTextField_hintText)
            val inputType = typedArray.getInt(R.styleable.CustomTextField_inputType, 0)
            val helperText = typedArray.getString(R.styleable.CustomTextField_helperText)
            val startIcon = typedArray.getResourceId(R.styleable.CustomTextField_startIcon, 0)
            val boxBackgroundColor = typedArray.getColor(
                R.styleable.CustomTextField_boxBackgroundColor,
                ContextCompat.getColor(context, android.R.color.white)
            )
            val hintTextColor = typedArray.getColor(
                R.styleable.CustomTextField_hintTextColor,
                ContextCompat.getColor(context, android.R.color.holo_purple)
            )
            val errorText = typedArray.getString(R.styleable.CustomTextField_errorText)
            val boxStrokeColor = typedArray.getColor(
                R.styleable.CustomTextField_boxStrokeColor,
                ContextCompat.getColor(context, android.R.color.holo_purple)
            )
            val maxLength = typedArray.getInt(R.styleable.CustomTextField_maxLength, -1)
            val scrollHorizontally =
                typedArray.getBoolean(R.styleable.CustomTextField_scrollHorizontally, false)
            val passwordToggleEnabled = typedArray.getBoolean(R.styleable.CustomTextField_passwordToggleEnabled, false)

            textInputLayout.hint = hintText
            textInputLayout.helperText = helperText
            textInputLayout.boxBackgroundColor = boxBackgroundColor
            textInputEditText.setHintTextColor(hintTextColor)
            textInputLayout.boxStrokeColor = boxStrokeColor
            textInputLayout.error = errorText
            if (startIcon != 0) {
                textInputLayout.setStartIconDrawable(startIcon)
            }

            if (passwordToggleEnabled) {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            }

            textInputEditText.inputType = when (inputType) {
                1 -> android.text.InputType.TYPE_CLASS_NUMBER
                2 -> android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
                3 -> android.text.InputType.TYPE_CLASS_DATETIME or android.text.InputType.TYPE_DATETIME_VARIATION_DATE
                else -> android.text.InputType.TYPE_CLASS_TEXT
            }




            if (maxLength != -1) {
                textInputEditText.filters =
                    maxLength.let { arrayOf(android.text.InputFilter.LengthFilter(it)) }
            }

            if (scrollHorizontally) {
                textInputEditText.isHorizontalScrollBarEnabled = true
                textInputEditText.setHorizontallyScrolling(true)
            }

            typedArray.recycle()
        }
    }

    fun getText(): String {
        return textInputEditText.text.toString()
    }

    fun setText(text: String) {
        textInputEditText.setText(text)
    }
}