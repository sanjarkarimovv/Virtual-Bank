package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.coroutines.channels.Channel
import uz.androbeck.virtualbank.databinding.CustomHomeHeaderBinding
import uz.androbeck.virtualbank.utils.extentions.invisible
import uz.androbeck.virtualbank.utils.extentions.visible

class CustomHomeHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private val binding = CustomHomeHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    var clicks: ((HeaderUiEvent) -> Unit)? = null
    private var isShowAmount = true
        set(value) {
            field = value
        }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        onClick()
    }


    fun setAmount(amount: String) {
        if (isShowAmount)
            binding.tvTextAmount.text = amount
        else return
    }

    private fun onClick() = with(binding) {
        btnShowAmount.setOnClickListener {
            binding.layoutAmountNotShowRoot.visible()
            binding.layoutAmountRoot.invisible()
            clicks?.invoke(HeaderUiEvent.ClickShowAmount(false))
        }
        btnShowAmountInNotShow.setOnClickListener {
            binding.layoutAmountNotShowRoot.invisible()
            binding.layoutAmountRoot.visible()
            clicks?.invoke(HeaderUiEvent.ClickShowAmount(true))
        }

        btnMore.setOnClickListener {
            clicks?.invoke(HeaderUiEvent.ClickMore)
        }
        btnCard.setOnClickListener {
            clicks?.invoke(HeaderUiEvent.ClickCards)
        }
        btnQrCode.setOnClickListener {
            clicks?.invoke(HeaderUiEvent.ClickQR)
        }
        btnNfsee.setOnClickListener {
            clicks?.invoke(HeaderUiEvent.ClickNfs)
        }
    }
}