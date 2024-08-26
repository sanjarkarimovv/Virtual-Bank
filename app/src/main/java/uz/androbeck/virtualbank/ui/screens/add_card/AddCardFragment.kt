package uz.androbeck.virtualbank.ui.screens.add_card

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentAddCardBinding
import uz.androbeck.virtualbank.databinding.ItemTabBinding
import uz.androbeck.virtualbank.domain.mock_data.AppHardcodeData
import uz.androbeck.virtualbank.domain.ui_models.card.AddCardReqUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.card_scanner.CardScannerBottomDialog
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.setTextColorRes
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class AddCardFragment : BaseFragment(R.layout.fragment_add_card) {

    private val binding by viewBinding(FragmentAddCardBinding::bind)
    private val vm: AddCardViewModel by viewModels()
    private var isKeyboardVisible = false

    @Inject
    lateinit var messageController: MessageController

    @SuppressLint("InflateParams")
    override fun setup() {
        with(binding) {
            etCardNumber.onScannerClick = {
                showCardScannerDialog()
            }
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
                editTextFocus(hasFocus, mcvValidityPeriod, validityPeriodHelperText)
            }

            etCardName.setOnFocusChangeListener { _, hasFocus ->
                editTextFocus(hasFocus, mcvCardName, cardNameHelperText)
            }

            viewPager.adapter = ViewPagerAdapter(AppHardcodeData.cardStyleImages)
            setupViewPager(viewPager)
            setupTabLayout(tabLayout, viewPager)

            lifecycleScope.launch {
                vm.isFormValid.collect { isValid ->
                    btnAddCard.isEnable = isValid
                }
            }
        }
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content).viewTreeObserver.addOnGlobalLayoutListener(
            on
        )
    }

    private fun showCardScannerDialog() {
        CardScannerBottomDialog().show(
            childFragmentManager,
            CardScannerBottomDialog::class.java.simpleName
        )
    }

    override fun clicks(): Unit = with(binding) {
        btnAddCard.onClick = {
            showProgress()
            val cardNumber = vm.resultCardNumber(etCardNumber.getCardNumber())
            val (month, year) = vm.extractDate(metValidityPeriod.text.toString())
            val cardName = etCardName.text.toString()
            val cardStyleColor = viewPager.currentItem
            val requestModel = AddCardReqUIModel(
                pan = cardNumber,
                expiredMonth = month,
                expiredYear = year,
                name = cardName
            )
            vm.addCard(requestModel)
        }
        toolbar.onClickLeftIcon = {
            findNavController().popBackStack()
        }
    }


    override fun observe() {
        vm.isAddCard.observe(viewLifecycleOwner) {
            if (it) {
                hideProgress()
                toast(getString(R.string.str_add_card_succes))
                findNavController().popBackStack()
            }
        }
        vm.isErrorEvent.observe(viewLifecycleOwner) {
            if (it) {
                hideProgress()
                addCardError()
                binding.etCardNumber.addCardError()
            }
        }
        messageController.observeMessage().onEach {
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun editTextFocus(hasFocus: Boolean, cardView: MaterialCardView, helperText: TextView) {
        val strokeWidthInDp =
            if (hasFocus) Constants.Number.SELECT_CARD_STROKE_WIDTH else Constants.Number.DEFAULT_CARD_STROKE_WIDTH
        val strokeWidthInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            strokeWidthInDp.toFloat(),
            resources.displayMetrics
        ).toInt()

        cardView.strokeWidth = strokeWidthInPx
        cardView.strokeColor = ContextCompat.getColor(
            requireContext(),
            if (hasFocus) R.color.colorPrimary else R.color.colorOutline
        )
        helperText.setTextColorRes(if (hasFocus) R.color.colorPrimary else R.color.colorScrim)
    }

    @SuppressLint("InflateParams")
    private fun setupTabLayout(tabLayout: TabLayout, viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
            val customView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_tab, null)
            val bindingItemTab = ItemTabBinding.bind(customView)
            bindingItemTab.tabIcon.setImageResource(AppHardcodeData.cardStyleImages[position])
            bindingItemTab.checkIcon.visibility = View.GONE
            tab.customView = customView
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.let {
                    val bindingItemTab = ItemTabBinding.bind(it)
                    bindingItemTab.checkIcon.visibility = View.VISIBLE
                    bindingItemTab.tabIcon.animate()?.scaleX(1.5f)?.scaleY(1.5f)?.setDuration(300)
                        ?.start()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.let {
                    val bindingItemTab = ItemTabBinding.bind(it)
                    bindingItemTab.checkIcon.visibility = View.GONE
                    bindingItemTab.tabIcon.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(300)
                        ?.start()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        viewPager.apply {
            offscreenPageLimit = Constants.Number.OFFSCREEN_PAGE_LIMIT
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.halfPageMargin) +
                        resources.getDimensionPixelOffset(R.dimen.peekOffset)
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }
    }

    private fun showProgress() = with(binding) {
        btnAddCard.isProgress = true
    }

    private fun hideProgress() = with(binding) {
        btnAddCard.isProgress = false
    }

    private fun validateFields() = with(binding) {
        vm.validateFields(
            etCardNumber.getCardNumber(),
            metValidityPeriod.text.toString(),
            etCardName.text.toString()
        )
    }

    private fun addCardError() = with(binding) {
        val strokeWidthInDp = Constants.Number.SELECT_CARD_STROKE_WIDTH
        val strokeWidthInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            strokeWidthInDp.toFloat(),
            resources.displayMetrics
        ).toInt()
        mcvValidityPeriod.strokeWidth = strokeWidthInPx
        mcvValidityPeriod.strokeColor = ContextCompat.getColor(requireContext(), R.color.colorError)
        validityPeriodHelperText.setTextColorRes(R.color.colorError)
    }

    private val on = ViewTreeObserver.OnGlobalLayoutListener {
        if (view == null || !isAdded) return@OnGlobalLayoutListener

        val rect = Rect()
        val rootView =
            requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height
        val keypadHeight = screenHeight - rect.bottom

        val isKeyboardNowVisible = keypadHeight > screenHeight * 0.15

        if (isKeyboardNowVisible != isKeyboardVisible) {
            isKeyboardVisible = isKeyboardNowVisible
            onKeyboardVisibilityChanged(isKeyboardVisible, keypadHeight)
        }
    }


    private fun onKeyboardVisibilityChanged(visible: Boolean, keypadHeight: Int) {
        if (view == null || !isAdded) return

        val params = binding.btnAddCard.layoutParams as ViewGroup.MarginLayoutParams
        if (visible) {
            params.bottomMargin = keypadHeight + binding.btnAddCard.height
        } else {
            params.bottomMargin = 20.dpToPx()
        }
        binding.btnAddCard.layoutParams = params
    }

    private fun Int.dpToPx(): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (this * density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.decorView.findViewById<ViewGroup>(android.R.id.content)
            .viewTreeObserver.removeOnGlobalLayoutListener(on)
    }
}