package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.add_card

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentAddCardBinding
import uz.androbeck.virtualbank.databinding.ItemTabBinding
import uz.androbeck.virtualbank.domain.mock_data.CardStyleColors
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.setTextColorRes
import uz.androbeck.virtualbank.utils.extentions.toast

class AddCardFragment : BaseFragment(R.layout.fragment_add_card) {

    private val binding by viewBinding(FragmentAddCardBinding::bind)

    @SuppressLint("InflateParams")
    override fun setup() {
        with(binding) {
            btnAddCard.isEnable = false

            etCardNumber.addTextChangedListener {
                validateFields()
            }

            metValidityPeriod.addTextChangedListener {
                validateFields()
            }

            etCardName.addTextChangedListener {
                validateFields()
            }

            metValidityPeriod.setOnFocusChangeListener { _, hasFocus ->
                editTextFocus(
                    hasFocus,
                    requireContext(),
                    mcvValidityPeriod,
                    validityPeriodHelperText
                )
            }
            etCardName.setOnFocusChangeListener { _, hasFocus ->
                editTextFocus(
                    hasFocus,
                    requireContext(),
                    mcvCardName,
                    cardNameHelperText
                )
            }
            viewPager.adapter = ViewPagerAdapter(CardStyleColors.cardStyleColors)

            viewPager.apply {
                offscreenPageLimit = 1

                val recyclerView = getChildAt(0) as RecyclerView

                recyclerView.apply {
                    val padding = resources.getDimensionPixelOffset(R.dimen.halfPageMargin) +
                            resources.getDimensionPixelOffset(R.dimen.peekOffset)
                    setPadding(padding, 0, padding, 0)
                    clipToPadding = false
                }
            }

            tabLayoutMediator(tabLayout, viewPager)

            tabLayoutSelected(tabLayout)

        }
    }

    override fun clicks(): Unit = with(binding) {
        btnAddCard.onClick = {
            showProgress()
            val cardNumber = resultCardNumber(etCardNumber.getCardNumber())
            val (month, year) = extractDate(binding.metValidityPeriod.text.toString())
            val cardName = etCardName.text.toString()
            toast("Karta raqami: $cardNumber\nOy: $month\nYil: $year\nKarta nomi: $cardName")
        }
    }

    private fun editTextFocus(
        hasFocus: Boolean,
        context: Context,
        cardView: MaterialCardView,
        helperText: TextView,
    ) {
        val strokeWidthInDp = if (hasFocus) 2 else 1
        val strokeWidthInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            strokeWidthInDp.toFloat(),
            resources.displayMetrics
        ).toInt()

        cardView.strokeWidth = strokeWidthInPx
        cardView.strokeColor = ContextCompat.getColor(
            context,
            if (hasFocus) R.color.screenTextColor else R.color.colorOutline
        )
        helperText.setTextColorRes(if (hasFocus) R.color.screenTextColor else R.color.colorScrim)
    }

    @SuppressLint("InflateParams")
    private fun tabLayoutMediator(tabLayout: TabLayout, viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
            val customView: View = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_tab, null)
            val bindingItemTab = ItemTabBinding.bind(customView)  // Bind the inflated view
            bindingItemTab.tabIcon.setImageResource(CardStyleColors.cardStyleColors[position])
            bindingItemTab.checkIcon.visibility = View.GONE
            tab.customView = customView
        }.attach()
    }

    private fun tabLayoutSelected(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.let { customView ->
                    val bindingItemTab = ItemTabBinding.bind(customView)
                    bindingItemTab.checkIcon.visibility = View.VISIBLE
                    bindingItemTab.tabIcon.animate()?.scaleX(1.5f)?.scaleY(1.5f)?.setDuration(300)
                        ?.start()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.let { customView ->
                    val bindingItemTab = ItemTabBinding.bind(customView)
                    bindingItemTab.checkIcon.visibility = View.GONE
                    bindingItemTab.tabIcon.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(300)
                        ?.start()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }


    private fun showProgress() {
        binding.btnAddCard.isProgress = true
    }

    private fun hideProgress() {
        binding.btnAddCard.isProgress = false
    }

    private fun validateFields() {
        val cardNumberLength = binding.etCardNumber.getCardNumber().length
        val validityPeriodLength = binding.metValidityPeriod.text.toString().length
        val cardNameLength = binding.etCardName.text.toString().length
        binding.btnAddCard.isEnable =
            cardNumberLength == 19 && validityPeriodLength == 5 && cardNameLength > 3
    }

    private fun resultCardNumber(inputString: String): String {
        val resultString = inputString.replace(" ", "")
        return resultString
    }

    private fun extractDate(inputString: String): Pair<String, String> {
        val (month, year) = inputString.split("/")
        val fullYear = "20$year"
        return month to fullYear
    }
}