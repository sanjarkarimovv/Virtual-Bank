package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.renderscript.ScriptGroup.Binding
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.androbeck.virtualbank.databinding.CustomHomeBodyBinding
import uz.androbeck.virtualbank.databinding.ItemCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.ui.screens.HomeComponents
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.HomeComponentsUiEvent
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

class CustomHomeBody @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private var binding = CustomHomeBodyBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.BOTTOM
        orientation = VERTICAL
    }



    fun isShow(components: List<UiComponents>) = with(binding) {
        components.forEach {
            when (it.name) {
                HomeComponents.Cards -> {
                    if (it.isShow) {
                        layoutCards.visible()
                    } else {
                        layoutCards.gone()
                    }
                }

                HomeComponents.Payments -> {
                    layoutPayments.visible()
                }

                HomeComponents.LastTransfers -> {
                    layoutLastTransfer.visible()
                }

                HomeComponents.PaymentForPhoneNumber -> {
                }

                HomeComponents.FinancesService -> {

                }

                HomeComponents.ForAdvertising -> {

                }
            }
        }
    }


}
/**
 *  components
 *  cards payments lastTransfer paymentForPhoneNumber financesService forAdvertising
 *
 *
 *
 *
 */