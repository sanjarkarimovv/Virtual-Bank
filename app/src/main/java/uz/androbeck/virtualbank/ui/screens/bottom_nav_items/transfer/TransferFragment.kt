package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.databinding.FragmentTransferBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.customViews.inputs.CustomViewEnterCardOrPhoneNumber

class TransferFragment : BaseFragment(R.layout.fragment_transfer) {
    private val binding by viewBinding(FragmentTransferBinding::bind)
    private val vm: TransferFragmentViewModel by viewModels()
    private lateinit var usersInfoAdapter: TransferUsersAdapter
    override fun setup() {

        usersInfoAdapter = TransferUsersAdapter { item ->

        }
        adjustButtonPositionForKeyboard()
        with(binding) {
            etCardOrPhoneNumber.addTextChangedListener {
                if (it?.length == 19 || it?.length == 16)
                    btnContinue.isEnable = true
                else
                    btnContinue.isEnable = false
            }
            etCardOrPhoneNumber.onClickEditText = {
                btnContinue.visibility = View.VISIBLE
                btnContinue.isEnable = false
                ivBack.visibility = View.VISIBLE
                mcvTransferHimself.visibility = View.GONE
                mcvGallery.visibility = View.GONE
                mcvTransferContacts.visibility = View.GONE

            }
            ivBack.setOnClickListener {
                etCardOrPhoneNumber.clearFocus()
                btnContinue.visibility = View.GONE
                ivBack.visibility = View.GONE
                mcvTransferHimself.visibility = View.VISIBLE
                mcvGallery.visibility = View.VISIBLE
                mcvTransferContacts.visibility = View.VISIBLE
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etCardOrPhoneNumber.windowToken, 0)
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

        setupOnBackPressed()
    }

    //    private fun adjustButtonPositionForKeyboard() {
//        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
//        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                val rect = Rect()
//                rootView.getWindowVisibleDisplayFrame(rect)
//                val screenHeight = rootView.height
//
//                val keypadHeight = screenHeight - rect.bottom
//
//                // If the keyboard is open
//                if (keypadHeight > screenHeight * 0.15) {
//                    binding.btnContinue.visibility = View.VISIBLE
//                    // Move the button above the keyboard
//                    binding.btnContinue.translationY = -keypadHeight.toFloat() + binding.btnContinue.height
//                } else {
//                    // Reset the button position when the keyboard is hidden
//                    binding.btnContinue.translationY = 0f
//                    binding.btnContinue.visibility = View.GONE
//                }
//            }
//        })
//    }
    private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val rect = Rect()
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height

        val keypadHeight = screenHeight - rect.bottom

        if (keypadHeight > screenHeight * 0.15) {
            binding.btnContinue.visibility = View.VISIBLE
            binding.btnContinue.translationY = -keypadHeight.toFloat() + binding.btnContinue.height
        } else {
            binding.btnContinue.translationY = 0f
        }
    }

    private fun adjustButtonPositionForKeyboard() {
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val etCardNumber = view?.findViewById<CustomViewEnterCardOrPhoneNumber>(R.id.et_card_number)
        etCardNumber?.onActivityResult(requestCode, resultCode, data)

    }

    private fun setupOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.etCardOrPhoneNumber.hasFocus()) {
                        binding.etCardOrPhoneNumber.clearFocus()
                        binding.btnContinue.visibility = View.GONE
                        binding.ivBack.visibility = View.GONE
                        binding.mcvTransferHimself.visibility = View.VISIBLE
                        binding.mcvGallery.visibility = View.VISIBLE
                        binding.mcvTransferContacts.visibility = View.VISIBLE
                    } else {
                        // Вызовите метод родительской активности
                        isEnabled = false // Чтобы не зациклить вызов
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }

}