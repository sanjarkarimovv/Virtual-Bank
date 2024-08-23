package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import uz.androbeck.virtualbank.databinding.CustomHomeHeaderBinding
import uz.androbeck.virtualbank.utils.extentions.gone
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
        if (isShowAmount) {
            binding.tvTextAmount.text = toSum(amount)
            binding.tvTextAmount.visible()
            binding.loadingTotalBalance.gone()

        }
        else return
    }

    fun toSum(str: String): CharSequence {
        var sum = StringBuilder()
        var count = 0
        for (c in str.reversed()) {
            sum.append(c)
            count++
            if (count == 3) {
                count = 0
                sum.append(" ")
            }
        }
        sum = sum.reverse()
        return sum
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