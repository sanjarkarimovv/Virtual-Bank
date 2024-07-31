package uz.androbeck.virtualbank.ui.customViews

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.LayoutLoadingButtonBinding
import uz.androbeck.virtualbank.utils.aliases.ColorRes
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

@SuppressLint("Recycle", "ResourceType")
class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        LayoutLoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var OnClick: (() -> Unit)? = null

    private var text: String? = null
        set(value) {
            field = value
            binding.btnCall.text = value
        }

    init {
        with(binding) {
            btnCall.setOnClickListener {
                btnCall.isEnabled = false
                btnCall.isClickable = false
                btnCall.text = ""
                progressBar.visible()
                OnClick?.invoke()
            }
            attrs?.let {
                val defaultWhiteColor = context.color(ColorRes.colorOnSecondary)
                val typedArray: TypedArray =
                    context.obtainStyledAttributes(it, R.styleable.LoadingButton)
                val text = typedArray.getString(R.styleable.LoadingButton_text)
                this@LoadingButton.text = text
                val textColor = typedArray.getColor(
                    R.styleable.LoadingButton_textColor,
                    defaultWhiteColor
                )
                btnCall.setTextColor(textColor)
                val progressBarTintColor =
                    typedArray.getColor(
                        R.styleable.LoadingButton_progressBarTintColor,
                        defaultWhiteColor
                    )
                //progressBar.progressTintList = context.colorStateList(progressBarTintColor)
                val btnBackgroundColor =
                    typedArray.getColor(
                        R.styleable.LoadingButton_backgroundColor,
                        context.color(ColorRes.colorPrimary)
                    )
                btnCall.setBackgroundColor(btnBackgroundColor)
                typedArray.recycle()
            }
        }
    }

    fun restoreButton() = with(binding) {
        btnCall.isEnabled = true
        btnCall.isClickable = true
        progressBar.gone()
        btnCall.text = this@LoadingButton.text
    }
}