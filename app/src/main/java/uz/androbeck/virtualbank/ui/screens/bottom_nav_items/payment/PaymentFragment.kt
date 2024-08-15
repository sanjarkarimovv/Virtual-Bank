package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment
import android.annotation.SuppressLint
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentPaymentBinding
import uz.androbeck.virtualbank.domain.mock_data.AppHardcodeData
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.hideKeyboard
class PaymentFragment : BaseFragment(R.layout.fragment_payment) {
    private val savedPaymentAdapter: SavedPaymentAdapter by lazy {
        SavedPaymentAdapter()
    }
    private val servicePaymentAdapter: ServicePaymentAdapter by lazy {
        ServicePaymentAdapter()
    }
    private val myHomeAdapter: MyHomeAdapter by lazy {
        MyHomeAdapter()
    }
    private val placesPaymentAdapter: PlacesPaymentAdapter by lazy {
        PlacesPaymentAdapter()
    }
    private val binding by viewBinding(FragmentPaymentBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun setup() {

        with(binding) {

            root.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }

            rvSavedPayments.adapter = savedPaymentAdapter
            savedPaymentAdapter.submitList(AppHardcodeData.savedPaymentList)

            rvServicePayments.adapter = servicePaymentAdapter
            servicePaymentAdapter.submitList(AppHardcodeData.servicePaymentList)

            rvMyHome.adapter = myHomeAdapter
            myHomeAdapter.submitList(AppHardcodeData.myHomePaymentList)

            rvPlacesPayments.adapter = placesPaymentAdapter
            placesPaymentAdapter.submitList(AppHardcodeData.placesPaymentList)
        }
    }
}
