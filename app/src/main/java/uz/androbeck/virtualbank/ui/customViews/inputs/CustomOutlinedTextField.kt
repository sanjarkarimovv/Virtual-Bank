package uz.androbeck.virtualbank.ui.customViews.inputs

import android.annotation.SuppressLint
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

@SuppressLint("RestrictedApi")
class CustomOutlinedTextField @JvmOverloads constructor(
    context: Context,
    attrs1: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs1, defStyleAttr) {

    private var textInputLayout: TextInputLayout
    var textInputEditText: TextInputEditText

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_outlined_text_field, this, true)

        textInputLayout = findViewById(R.id.text_input_layout_outlined)
        textInputEditText = findViewById(R.id.text_input_edit_text_outlined)

        attrs1?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomTextField)
            val hintText = typedArray.getString(R.styleable.CustomTextField_hintText)
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
            val clearable = typedArray.getBoolean(R.styleable.CustomTextField_clearable, false)
            val boxStrokeColor = typedArray.getColor(R.styleable.CustomTextField_boxStrokeColor, ContextCompat.getColor(context, android.R.color.holo_purple))
            val maxLength = typedArray.getInt(R.styleable.CustomTextField_maxLength, -1)
            val scrollHorizontally = typedArray.getBoolean(R.styleable.CustomTextField_scrollHorizontally, false)

            textInputLayout.hint = hintText
            textInputLayout.helperText = helperText
            textInputLayout.boxBackgroundColor = boxBackgroundColor
            textInputEditText.setHintTextColor(hintTextColor)
            textInputLayout.boxStrokeColor = boxStrokeColor
            textInputLayout.error = errorText
            if (startIcon != 0) {
                textInputLayout.setStartIconDrawable(startIcon)
            }



            if (clearable) {
                textInputLayout.setEndIconDrawable(R.drawable.icon1_36)
                textInputLayout.setEndIconOnClickListener {
                    textInputEditText.text?.clear()
                }

                    textInputEditText.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            s: CharSequence?,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {
                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                        }

                        override fun afterTextChanged(s: Editable?) {
                            if (!s.isNullOrEmpty()) {
                                textInputLayout.isEndIconVisible = true
                            } else {
                                textInputLayout.isEndIconVisible = false
                            }
                        }
                    })
            }

            if (maxLength != -1) {
                textInputEditText.filters = maxLength.let { arrayOf(android.text.InputFilter.LengthFilter(it)) }
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