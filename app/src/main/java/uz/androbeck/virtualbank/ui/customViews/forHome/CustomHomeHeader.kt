package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import uz.androbeck.virtualbank.databinding.CustomHomeHeaderBinding

class CustomHomeHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private val binding = CustomHomeHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }
}