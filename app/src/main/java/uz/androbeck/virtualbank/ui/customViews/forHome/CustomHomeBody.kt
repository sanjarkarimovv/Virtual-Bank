package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomHomeBodyBinding
import uz.androbeck.virtualbank.domain.ui_models.home.AdvertisingModel
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.ui.screens.HomeComponents
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

class CustomHomeBody @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private var binding = CustomHomeBodyBinding.inflate(LayoutInflater.from(context), this, true)

    private val counter = object : CountDownTimer(21000L, 3000) {
        private var count = 0
        override fun onTick(p0: Long) {
            if (count < 5) {
                binding.rvAdvertising.currentItem = count
                count++
            } else {
                count = 0
            }
        }

        override fun onFinish() {
            start()
        }

    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.BOTTOM
        orientation = VERTICAL
    }

    val cardsAdapter by lazy {
        CardsAdapter()
    }
    val paymentsAdapter by lazy {
        PaymentsAdapter()
    }
    val lastTransferAdapter by lazy {
        LastTransferAdapter()
    }
    private val advertisingAdapter by lazy {
        AdvertisingAdapter(
            listOf(
                AdvertisingModel(R.drawable.bg_banner_image4),
                AdvertisingModel(R.drawable.bg_banner_image2),
                AdvertisingModel(R.drawable.bg_banner_image5),
                AdvertisingModel(R.drawable.bg_banner_image),
                AdvertisingModel(R.drawable.bg_banner_image1),
            )
        )
    }


    fun isShow(components: List<UiComponents>) = with(binding) {
        components.forEach {
            when (it.name) {
                HomeComponents.Cards -> {
                    if (it.isShow) {
                        layoutCards.visible()
                        rvCards.adapter = cardsAdapter
                    } else {
                        layoutCards.gone()
                    }
                }

                HomeComponents.Payments -> {
                    if (it.isShow) {
                        layoutPayments.visible()
                        rvPayments.adapter = paymentsAdapter
                    } else {
                        layoutPayments.gone()
                    }
                }

                HomeComponents.LastTransfers -> {
                    if (it.isShow) {
                        layoutLastTransfer.visible()
                        rvLastTransfer.adapter = lastTransferAdapter
                    } else {
                        layoutLastTransfer.gone()
                    }
                }

                HomeComponents.PaymentForPhoneNumber -> {
                    layoutPaymentForPhoneNumber.isShow(isShow = it.isShow)
                }

                HomeComponents.FinancesService -> {
                    layoutFinancesService.isShow(it.isShow)
                }

                HomeComponents.ForAdvertising -> {
                    layoutAdvertising.isShow(it.isShow)
                    rvAdvertising.adapter = advertisingAdapter
                    counter.start()
                }
            }
        }

    }

    private fun LinearLayout.isShow(isShow: Boolean) {
        if (isShow) {
            this.visible()
        } else this.gone()
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