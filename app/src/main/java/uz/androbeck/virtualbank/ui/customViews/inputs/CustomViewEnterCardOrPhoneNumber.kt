package uz.androbeck.virtualbank.ui.customViews.inputs

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomViewEnterCardNumberBinding
import uz.androbeck.virtualbank.ui.enums.CardType
import uz.androbeck.virtualbank.ui.enums.PhoneNumberType
import uz.androbeck.virtualbank.utils.extentions.setTextColorRes
import uz.androbeck.virtualbank.utils.extentions.singleClickable

class CustomViewEnterCardOrPhoneNumber @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private val binding by lazy {
        CustomViewEnterCardNumberBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var onClickEditText: () -> Unit = {}
    var onScannerClick: () -> Unit = {}

    init {
        setUpView()
        binding.ivScanCard.singleClickable {
            onScannerClick
        }
        Log.d("TAG", "CustomViewEnterCardOrPhoneNumber:${setUpView()} ")
    }

    private fun setUpView() {
        with(binding) {
            metCardPhoneNumber.setOnClickListener {
            }
            metCardPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    onClickEditText()
                }
                val strokeWidthInDp = if (hasFocus) 2 else 1
                val strokeWidthInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    strokeWidthInDp.toFloat(),
                    resources.displayMetrics
                ).toInt()
                mcvCard.strokeWidth = strokeWidthInPx
                mcvCard.strokeColor = ContextCompat.getColor(
                    context,
                    if (hasFocus) R.color.colorPrimary else R.color.colorOutline
                )
                tvTitleInputCardNumber.setTextColorRes(if (hasFocus) R.color.colorPrimary else R.color.colorOutline)
            }
            metCardPhoneNumber.addTextChangedListener { s ->
                if (s.toString().isNotEmpty()) {
                    ivScanCard.setImageResource(R.drawable.ic_cancel)
                } else {
                    ivScanCard.setImageResource(R.drawable.ic_credit_card_scan)
                }

                val str = s.toString().filter { it.isDigit() }
                println("str: $str")
                if (s != null && s.length >= 4 && s.toString().substring(0, 3) != "998") {
                    // Your existing logic for card number
                    println("Card Number: $str")
                    //metCardPhoneNumber.setMask("#### #### #### ####")
                    metCardPhoneNumber.filters = arrayOf(InputFilter.LengthFilter(19))
                    val cardType = if (str.length >= 4) CardType.fromCardNumber(
                        str.substring(
                            0,
                            4
                        )
                    ) else CardType.UNKNOWN
                    when (cardType) {
                        CardType.UZCARD -> {

                            ivCardType.setImageResource(R.drawable.img_uzcard_logo)
                        }

                        CardType.HUMO -> {
                            ivCardType.setImageResource(R.drawable.img_humo_logo)
                        }

                        CardType.UNKNOWN -> {
                            ivCardType.setImageResource(R.drawable.ic_card)
                        }

                        CardType.AllCARDS -> {
                            ivCardType.setImageResource(R.drawable.ic_card)
                        }
                    }

                } else {
                    println("Phone Number: $str")
                    // Your existing logic for phone number
                   // metCardPhoneNumber.setMask("### ## ### ## ##")
                    metCardPhoneNumber.filters = arrayOf(InputFilter.LengthFilter(12))
                    val phoneNumber = if (str.length >= 5)
                        PhoneNumberType.fromPhoneNumber(str.substring(0, 5))
                    else PhoneNumberType.UNKNOWN
                    when (phoneNumber) {
                        PhoneNumberType.UzMobile -> {
                            ivCardType.setImageResource(R.drawable.img_uz_mobile)
                        }

                        PhoneNumberType.Beeline -> {
                            ivCardType.setImageResource(R.drawable.img_beeline)
                        }

                        PhoneNumberType.Ucell -> {
                            ivCardType.setImageResource(R.drawable.ucell_logo)
                        }

                        PhoneNumberType.Mobiuz -> {
                            ivCardType.setImageResource(R.drawable.img_mobiuz)
                        }

                        PhoneNumberType.Humons -> {
                            ivCardType.setImageResource(R.drawable.img_humans)
                        }

                        PhoneNumberType.UNKNOWN -> {
                            ivCardType.setImageResource(R.drawable.ic_card)
                        }
                    }
                }

                /*if (s?.toString()?.substring(0,3) != "998"){

                    }
                }else {

                    val phoneNumber = if (str.length >= 5)
                        PhoneNumberType.fromPhoneNumber(str.substring(0, 5))
                    else PhoneNumberType.UNKNOWN
                    when (phoneNumber) {
                        PhoneNumberType.UzMobile -> {
                            ivCardType.setImageResource(R.drawable.img_uz_mobile)
                        }

                        PhoneNumberType.Beeline -> {
                            ivCardType.setImageResource(R.drawable.img_beeline)
                        }

                        PhoneNumberType.Ucell -> {
                            ivCardType.setImageResource(R.drawable.ucell_logo)
                        }

                        PhoneNumberType.Mobiuz -> {
                            ivCardType.setImageResource(R.drawable.img_mobiuz)
                        }

                        PhoneNumberType.Humons -> {
                            ivCardType.setImageResource(R.drawable.img_humans)
                        }

                        PhoneNumberType.UNKNOWN -> TODO()
                    }
                }*/

                if (s.toString().isNotEmpty()) {
                    ivScanCard.singleClickable {
                        metCardPhoneNumber.text?.clear()
                    }
                } else {
                    ivScanCard.singleClickable {
                        //TODO() Permesion yozishkerak va kamera ochlishi kerak kartani scanb kilishi kerak
                        onScannerClick
                        println("Scan")
                    }

                }

            }
            ivScanCard.singleClickable {
                onScannerClick
                //TODO() Permesion yozishkerak va kamera ochlishi kerak kartani scanb kilishi kerak
                println("Scan")
            }
        }
    }

    fun getCardNumber(): String {
        return binding.metCardPhoneNumber.text.toString()
    }

    fun addTextChangedListener(afterTextChanged: (Editable?) -> Unit) {
        binding.metCardPhoneNumber.addTextChangedListener(afterTextChanged = afterTextChanged)
    }



}

