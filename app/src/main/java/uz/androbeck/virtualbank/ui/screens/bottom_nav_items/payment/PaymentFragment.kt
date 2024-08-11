package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.annotation.SuppressLint
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.test.MyList
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentPaymentBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

class PaymentFragment : BaseFragment(R.layout.fragment_payment) {



    private val savedPaymentAdapter: SavedPaymentAdapter by lazy {
        SavedPaymentAdapter()
    }
    private val servesPaymentAdapter: ServesPaymentAdapter by lazy {
        ServesPaymentAdapter()
    }
    private val myHomeAdapter: MyHomeAdapter by lazy {
        MyHomeAdapter()
    }
    private val placesPaymentAdapter: PlacesPaymentAdapter by lazy {
        PlacesPaymentAdapter()
    }
    private val bi by viewBinding(FragmentPaymentBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun setup() {
        with(bi) {


            root.setOnTouchListener { _, _ ->

                hideKeyboard()
                false
            }

            rvSavedPayments.adapter = savedPaymentAdapter
            savedPaymentAdapter.submitList(MyList.savedPaymentList)

            rvServersPayments.adapter = servesPaymentAdapter
            servesPaymentAdapter.submitList(MyList.servesPaymentList)

            rvMyHome.adapter = myHomeAdapter
            myHomeAdapter.submitList(MyList.myHomeList)

            rvPlacesPayments.adapter = placesPaymentAdapter
            placesPaymentAdapter.submitList(MyList.placesList)
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
