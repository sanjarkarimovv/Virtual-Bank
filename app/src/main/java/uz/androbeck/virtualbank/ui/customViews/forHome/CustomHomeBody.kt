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
import uz.androbeck.virtualbank.databinding.CustomHomeBodyBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
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
    var binding = CustomHomeBodyBinding.inflate(LayoutInflater.from(context), this, true)

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

    fun lastTransferDefIconShow(isShow: Boolean) {
        binding.icLastTransferInNotFound.isShow(isShow)
    }

    fun cardsAdapterSubmitList(list: List<CardUIModel>) {
        cardsAdapter.submitList(list)
        if (list.isEmpty()) {
            binding.icCardNotFound.visible()
            binding.icLoadingCards.gone()
        } else {
            binding.icCardNotFound.gone()
            binding.icLoadingCards.gone()
        }
    }

    private val cardsAdapter by lazy {
        CardsAdapter()
    }
    val paymentsAdapter by lazy {
        PaymentsAdapter()
    }
    val lastTransferAdapter by lazy {
        LastTransferAdapter()
    }

    fun advertisingAdapterLoadList(list: List<AdvertisingModel>) {
        advertisingAdapter.submitList(list)
    }

    private val advertisingAdapter by lazy {
        val action:()->Unit = {
            binding.icLoading.gone()
        }
        AdvertisingAdapter(
            action,binding.rvAdvertising
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
                }
            }
        }

    }

    private fun View.isShow(isShow: Boolean) {
        if (isShow) {
            this.visible()
        } else this.gone()
    }



}
/**
 *  components
 *  cards payments lastTransfer paymentForPhoneNumber financesService forAdvertising
 */