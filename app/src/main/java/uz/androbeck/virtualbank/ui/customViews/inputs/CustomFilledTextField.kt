package uz.androbeck.virtualbank.ui.customViews.inputs

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uz.androbeck.virtualbank.R

class CustomFilledTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var textInputLayout: TextInputLayout
    private var textInputEditText: TextInputEditText

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_filled_text_field, this, true)

        textInputLayout = findViewById(R.id.text_input_layout_filled)
        textInputEditText = findViewById(R.id.text_input_edit_text_filled)

        attrs?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomTextField)
            val hintText = typedArray.getString(R.styleable.CustomTextField_hintText)
            val inputType = typedArray.getInt(R.styleable.CustomTextField_inputType, 0)
            val maxLength = typedArray.getInt(R.styleable.CustomTextField_maxLength, -1)
            val helperText = typedArray.getString(R.styleable.CustomTextField_helperText)
            val endIcon = typedArray.getResourceId(R.styleable.CustomTextField_endIcon, 0)
            val errorText = typedArray.getString(R.styleable.CustomTextField_errorText)
            val clearable = typedArray.getBoolean(R.styleable.CustomTextField_clearable, false)
            val boxBackgroundColor = typedArray.getColor(
                R.styleable.CustomTextField_boxBackgroundColor,
                ContextCompat.getColor(context, android.R.color.white)
            )
            val boxStrokeColor = typedArray.getColor(
                R.styleable.CustomTextField_boxStrokeColor,
                ContextCompat.getColor(context, android.R.color.holo_purple)
            )
            val hintTextColor = typedArray.getColor(
                R.styleable.CustomTextField_hintTextColor,
                ContextCompat.getColor(context, android.R.color.holo_purple)
            )
            textInputLayout.hint = hintText
            textInputLayout.helperText = helperText
            textInputLayout.error = errorText
            textInputLayout.boxBackgroundColor = boxBackgroundColor
            textInputEditText.setHintTextColor(hintTextColor)
            textInputLayout.boxStrokeColor = boxStrokeColor

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

            if (endIcon != 0) {
                textInputLayout.setEndIconDrawable(endIcon)
            }

            if (clearable) {
                textInputLayout.setEndIconDrawable(R.drawable.icon1_36)
                textInputLayout.setEndIconOnClickListener {
                    textInputEditText.text?.clear()
                }
            }

            textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) {
                        textInputLayout.isEndIconVisible = true
                    } else {
                        textInputLayout.isEndIconVisible = false
                    }
                }
            })

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