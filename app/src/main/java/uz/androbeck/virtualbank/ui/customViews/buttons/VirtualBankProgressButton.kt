package uz.androbeck.virtualbank.ui.customViews.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ViewVirtualBankProgressButtonBinding

class VirtualBankProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = R.style.VirtualBankButton_Filled
) : FrameLayout(context, attrs, defStyle, defStyleRes) {
    private val binding =
        ViewVirtualBankProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var onClick
        get() = binding.button.onClick
        set(value) {
            binding.button.onClick = value
        }

    var isEnable: Boolean = false
        set(value) {
            field = value
            binding.button.isClickable = value
            binding.button.isEnabled = value
        }

    var text: CharSequence? = null
        set(value) {
            field = value
            binding.button.text = value
        }

    var isProgress: Boolean = false
        set(value) {
            field = value
            binding.button.isClickable = !value
            binding.button.text = text.takeIf { !value }
            binding.progress.isVisible = value
        }

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.VirtualBankProgressButton,
            defStyle,
            defStyleRes
        ).apply {
            text = getText(R.styleable.VirtualBankProgressButton_android_text)
        }.also {
            it.recycle()
        }
        isProgress = false
    }
}
