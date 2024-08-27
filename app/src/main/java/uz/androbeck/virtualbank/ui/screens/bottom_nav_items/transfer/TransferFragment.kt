package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.databinding.FragmentTransferBinding
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanReqUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class TransferFragment : BaseFragment(R.layout.fragment_transfer) {
    private val binding by viewBinding(FragmentTransferBinding::bind)
    private val vm: TransferFragmentViewModel by viewModels()
    private lateinit var usersInfoAdapter: TransferUsersAdapter
    override fun setup() {

        usersInfoAdapter = TransferUsersAdapter { item ->

        }
        adjustButtonPositionForKeyboard()
        with(binding) {
            etCardOrPhoneNumber.addTextChangedListener { enterNumber ->
                if (enterNumber?.length == 19) {
                    postReq(enterNumber.toString())
                    println("enterNumber: $enterNumber")

                } else {
                    btnContinue.isEnable = false
                    tvUserInfo.visibility = View.GONE
                    tvUserInfo.text = ""
                }
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
                tvUserInfo.visibility = View.GONE
                tvUserInfo.text = ""
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

    private  fun postReq(enterNumber: String) = with(binding) {
        val getCardOwnerByPanReqUIModel =
            GetCardOwnerByPanReqUIModel(enterNumber.replace(" ", ""))
        vm.apply {
            if (!isRequestInProgress) {
                getCardOwnerByPan(getCardOwnerByPanReqUIModel)

                isRequestInProgress = true
            } else {

                toast("Запрос уже отправляется")
            }
        }

    }
    override fun observe() = with(binding) {
        vm.cardOwnerResponse.observe(viewLifecycleOwner) {
            if (it) {
                vm.isGetCardOwnerByPanEvent.observe(viewLifecycleOwner) { ownerName ->
                tvUserInfo.visibility = View.VISIBLE
                    if (ownerName.equals("Karta Mavjud emas")) {
                        btnContinue.isEnable = false
                        tvUserInfo.text = ownerName
                    } else {
                        tvUserInfo.text = ownerName
                        btnContinue.isEnable = true
                    }
                }
            } else {
                tvUserInfo.visibility = View.GONE
                btnContinue.isEnable = false
            }
        }
   }

    private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val rect = Rect()
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height

        val keypadHeight = screenHeight - rect.bottom

        if (keypadHeight > screenHeight * 0.15) {
            binding.btnContinue.translationY = -keypadHeight.toFloat() + binding.btnContinue.height
        } else {
            binding.btnContinue.translationY = 0f
        }
    }

    private fun adjustButtonPositionForKeyboard() {
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
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
                        binding.tvUserInfo.visibility = View.GONE
                        binding.tvUserInfo.text = ""
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