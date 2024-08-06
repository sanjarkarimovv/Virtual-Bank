package uz.androbeck.virtualbank.ui.customViews.edit_texts

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomEnterCodeEditTextBinding

@SuppressLint("Recycle", "ResourceType", "CustomViewStyleable")
class VirtualBankEnterCodeInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        CustomEnterCodeEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    }
    var nextEditText: VirtualBankEnterCodeInput? = null
    var previousEditText: VirtualBankEnterCodeInput? = null

    init {
        attrs?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomEnterCodeEditText)

            try {
                val boxBackgroundColor = typedArray.getColor(
                    R.styleable.CustomEnterCodeEditText_verifyBoxBackgroundColor,
                    ContextCompat.getColor(context, R.color.colorSurface)
                )
                val boxStrokeColor = typedArray.getColor(
                    R.styleable.CustomEnterCodeEditText_verifyBoxStrokeColor,
                    ContextCompat.getColor(context, R.color.colorPrimary)
                )
                binding.nameTextInput.boxBackgroundColor = boxBackgroundColor
                binding.nameTextInput.boxStrokeColor = boxStrokeColor
            } finally {
                typedArray.recycle()
            }

            binding.nameEditText.addTextChangedListener { s ->
                if (s?.length == 1) {
                    nextEditText?.requestFocus()
                } else if (s?.length == 0) {
                    previousEditText?.requestFocus()
                }
            }
        }
    }

    fun addTextChangedListeners(watcher: TextWatcher) {
        binding.nameEditText.addTextChangedListener(watcher)
    }
}
