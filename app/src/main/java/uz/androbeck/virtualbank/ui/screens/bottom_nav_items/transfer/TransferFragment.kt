package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.databinding.FragmentTransferBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.customViews.inputs.CustomViewEnterCardOrPhoneNumber
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible

class TransferFragment : BaseFragment(R.layout.fragment_transfer) {
    private val binding by viewBinding(FragmentTransferBinding::bind)
    private val vm: TransferFragmentViewModel by viewModels()
    private lateinit var usersInfoAdapter: TransferUsersAdapter
    override fun setup() {

        usersInfoAdapter = TransferUsersAdapter { item ->

        }

        with(binding) {
            etCardOrPhoneNumber.onClickEditText = {
                btnContinue.visibility = View.VISIBLE
                ivBack.visibility = View.VISIBLE

            }
            ivBack.setOnClickListener {
                etCardOrPhoneNumber.clearFocus()
                btnContinue.visibility = View.GONE
                ivBack.visibility = View.GONE
            }
            rvSavedUsers.adapter = usersInfoAdapter
            usersInfoAdapter.submitList(
                listOf(
                    AddCardReqDto(
                        pan = null,
                        expired_year = null,
                        expired_month = null,
                        name = "Baxtiyorov I.O."
                    ),
                    AddCardReqDto(
                        pan = null,
                        expired_year = null,
                        expired_month = null,
                        name = "Xursanov I.O."
                    ),
                    AddCardReqDto(
                        pan = null,
                        expired_year = null,
                        expired_month = null,
                        name = "Quvonchev I.O."
                    ),
                    AddCardReqDto(
                        pan = null,
                        expired_year = null,
                        expired_month = null,
                        name = ""
                    )
                )
            )

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val etCardNumber = view?.findViewById<CustomViewEnterCardOrPhoneNumber>(R.id.et_card_number)
        etCardNumber?.onActivityResult(requestCode, resultCode, data)

    }
}