package uz.androbeck.virtualbank.ui.customViews.inputs

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
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

    private val CAMERA_REQUEST_CODE = 100
    private val binding by lazy {
        CustomViewEnterCardNumberBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var onClickEditText: () -> Unit = {}

    init {
        setUpView()
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
                        checkCameraPermission()
                        println("Scan")
                    }

                }

            }
            ivScanCard.singleClickable {
                //TODO() Permesion yozishkerak va kamera ochlishi kerak kartani scanb kilishi kerak
                checkCameraPermission()
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

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                (context as Activity),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(context.packageManager) != null) {
            (context as Activity).startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            scanCard(imageBitmap)
        }
    }

    private fun scanCard(imageBitmap: Bitmap) {
        val inputImage = InputImage.fromBitmap(imageBitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(inputImage)
            .addOnSuccessListener { visionText ->
                parseCardData(visionText)
            }
            .addOnFailureListener { e ->
                // Обработка ошибок
                println("Ошибка распознавания: ${e.message}")
            }
    }

    private fun parseCardData(visionText: Text) {
        // Пример простого парсинга текста
        for (block in visionText.textBlocks) {
            val blockText = block.text
            // Используйте регулярные выражения для поиска номера карты, имени и фамилии
            println("Распознанный текст: $blockText")
        }
    }
}

