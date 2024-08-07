package uz.androbeck.virtualbank.ui.customViews.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ViewVirtualBankInputSimpleBinding

class VirtualBankInputSimple @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = R.style.VirtualBankInput
) : FrameLayout(context, attrs, defStyle, defStyleRes) {
    val inputLayout get() = binding.textInputLayout
    val editText get() = binding.editText

    var hint
        get() = inputLayout.hint
        set(value) {
            inputLayout.hint = value
        }

    var helperText
        get() = inputLayout.helperText
        set(value) {
            inputLayout.helperText = value
        }

    var error
        get() = inputLayout.error
        set(value) {
            inputLayout.error = value
        }

    var inputType
        get() = editText.inputType
        set(value) {
            editText.inputType = value
        }

    var keyListener
        get() = editText.keyListener
        set(value) {
            editText.keyListener = value
        }

    private val binding =
        ViewVirtualBankInputSimpleBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.VirtualBankInputSimple,
            defStyle,
            defStyleRes
        ).apply {
            try {
                hint = getString(R.styleable.VirtualBankInputSimple_hintText)
                helperText = getString(R.styleable.VirtualBankInputSimple_helperText)
                inputLayout.isErrorEnabled =
                    getBoolean(R.styleable.VirtualBankInputSimple_errorEnabled, true)
            } finally {
                recycle()
            }
        }
    }

    fun clear() {
        editText.text = null
    }
}
