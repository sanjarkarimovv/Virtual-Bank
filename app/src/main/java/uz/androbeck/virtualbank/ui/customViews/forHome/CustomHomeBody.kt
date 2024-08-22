package uz.androbeck.virtualbank.ui.customViews.forHome

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
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

    private var isCounter = true
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
            count = 0
            isCounter = true
            startCounter()
        }
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.BOTTOM
        orientation = VERTICAL
        setup()
    }

    private fun setup() {
        binding.rvAdvertising.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = ViewPager2.OVER_SCROLL_NEVER
            val composition = CompositePageTransformer()
            composition.addTransformer(MarginPageTransformer(0))
            composition.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.95f + r * 0.14f
            }
            setPageTransformer(composition)
        }
    }

    fun cardsDefIconShow(isShow: Boolean) {
        binding.icCardNotFound.isShow(isShow)
    }

    fun lastTransferDefIconShow(isShow: Boolean) {
        binding.icLastTransferInNotFound.isShow(isShow)
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
                    startCounter()
                }
            }
        }

    }

    private fun View.isShow(isShow: Boolean) {
        if (isShow) {
            this.visible()
        } else this.gone()
    }

    fun stopCounter() {
        counter.cancel()
    }

    private fun startCounter() {
        if (isCounter){
            counter.start()
            isCounter = false
        }
    }


}
/**
 *  components
 *  cards payments lastTransfer paymentForPhoneNumber financesService forAdvertising
 */