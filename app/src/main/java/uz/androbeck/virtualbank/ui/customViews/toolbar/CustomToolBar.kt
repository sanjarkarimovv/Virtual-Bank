package uz.androbeck.virtualbank.ui.customViews.toolbar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomToolbarBinding
import uz.androbeck.virtualbank.utils.extentions.singleClickable


@SuppressLint("CustomViewStyleable", "Recycle")
class CustomToolBar @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : MaterialToolbar(context, attrs, defStyleAttrs) {


    private val binding by lazy {
        CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        attrs?.let {
            val typedArray: TypedArray = context.obtainStyledAttributes(
                it, R.styleable.VirtualBankToolbar
            )

            val backgroundColor = typedArray.getColor(
                R.styleable.VirtualBankToolbar_toolbarColor,
                ContextCompat.getColor(context, R.color.static_white)
            )
            val toolbarTitle = typedArray.getString(R.styleable.VirtualBankToolbar_toolbarTitle)
            val toolbarText = typedArray.getString(R.styleable.VirtualBankToolbar_toolbarText)
            val icon = typedArray.getResourceId(
                R.styleable.VirtualBankToolbar_Icon, 0
            )
            val textColor = typedArray.getColor(
                R.styleable.VirtualBankToolbar_toolbarTitleTextColor,
                ContextCompat.getColor(context, R.color.colorScrim)
            )

            val endIcon = typedArray.getResourceId(
                R.styleable.VirtualBankToolbar_toolbarEndIcon, 0
            )

            binding.customToolbar.setBackgroundColor(backgroundColor)
            binding.customToolbar.setNavigationIcon(icon)
            binding.toolbarText.setTextColor(textColor)
            binding.customToolbar.setBackgroundColor(Color.TRANSPARENT)

            if(toolbarTitle==""){
                binding.toolbarText.text = toolbarTitle
            }else{
                binding.toolbarText.text = toolbarText
            }

            if (endIcon != 0) {
                binding.rightIcon.setImageResource(endIcon)
                binding.rightIcon.visibility = View.VISIBLE
            } else {
                binding.rightIcon.visibility = View.GONE
            }

            //Toolbarni back button bosilish
            binding.customToolbar.singleClickable {
                arrowBack()
            }

            // Toolbarni icon onclick
            binding.rightIcon.singleClickable{
                rightIcon()
            }
            typedArray.recycle()
        }
    }

    fun arrowBack() {
        Toast.makeText(context, "Arrow Back", Toast.LENGTH_SHORT).show()
    }

    fun rightIcon() {
        Toast.makeText(context, "Right Icon", Toast.LENGTH_SHORT).show()
    }
}
