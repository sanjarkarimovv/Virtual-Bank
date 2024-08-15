package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import uz.androbeck.virtualbank.databinding.CustomHomeHeaderBinding
import uz.androbeck.virtualbank.utils.extentions.invisible
import uz.androbeck.virtualbank.utils.extentions.visible

class CustomHomeHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private val binding = CustomHomeHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    private val _clicks = Channel<HeaderUiEvent>()
    val clicks = _clicks.consumeAsFlow()
    private var isShowAmount = true
        set(value) {
            field = value

        }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        onClick()
        setup()
    }

    private fun setup() {

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
            _clicks.trySend(HeaderUiEvent.ClickShowAmount(false))
        }
        btnShowAmountInNotShow.setOnClickListener {
            binding.layoutAmountNotShowRoot.invisible()
            binding.layoutAmountRoot.visible()
            _clicks.trySend(HeaderUiEvent.ClickShowAmount(true))
        }

        btnMore.setOnClickListener {
            _clicks.trySend(HeaderUiEvent.ClickMore)
        }
        btnCard.setOnClickListener {
            _clicks.trySend(HeaderUiEvent.ClickCards)
        }
        btnQrCode.setOnClickListener {
            _clicks.trySend(HeaderUiEvent.ClickQR)
        }
        btnNfsee.setOnClickListener {
            _clicks.trySend(HeaderUiEvent.ClickNfs)
        }
    }
}