package uz.androbeck.virtualbank.ui.customViews.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.utils.extentions.singleClickable

class VirtualBankButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = R.style.VirtualBankButton_Filled
) : AppCompatButton(context, attrs, defStyle) {

    var onClick: ((View) -> Unit)? = null

    init {
        singleClickable {
            onClick?.invoke(it)
        }
    }
}
