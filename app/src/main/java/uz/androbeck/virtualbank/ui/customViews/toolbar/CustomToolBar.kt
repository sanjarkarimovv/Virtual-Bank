package uz.androbeck.virtualbank.ui.customViews.toolbar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomToolbarBinding
import uz.androbeck.virtualbank.utils.extentions.singleClickable


@SuppressLint("CustomViewStyleable", "Recycle")
class CustomToolBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : MaterialToolbar(context, attrs, defStyleAttrs) {

    private val binding by lazy {
        CustomToolbarBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        attrs?.let {
            val typedArray: TypedArray = context.obtainStyledAttributes(
                it, R.styleable.VirtualBankToolbar,// defStyleAttrs, 0
            )
            val backgroundColor = typedArray.getColor(
                R.styleable.VirtualBankToolbar_toolbarColor,
                ContextCompat.getColor(context, android.R.color.white)
            )
            val text = typedArray.getString(R.styleable.VirtualBankToolbar_title)

            val icon = typedArray.getResourceId(R.styleable.VirtualBankToolbar_toolbarEndIcon, 0)

            val navigationIcon =
                typedArray.getResourceId(R.styleable.VirtualBankToolbar_navigationIcon, 0)

            // toolbar colors set
            binding.customToolbar.setBackgroundColor(backgroundColor)
            // toolbar end icons set
            binding.endIcon.setImageResource(icon)
            //toolbar title set
            binding.tvTitle.text = text
            if (navigationIcon != 0) {
                binding.customToolbar.setNavigationIcon(navigationIcon)
            }

            if (icon != 0) {
                binding.endIcon.setImageResource(icon)
                binding.endIcon.visibility = View.VISIBLE
            } else {
                binding.endIcon.visibility = View.GONE
            }

            //Toolbarni back button bosilish
            binding.customToolbar.setNavigationOnClickListener {
                arrowBack()
            }

            // Toolbarni icon onclick
            binding.endIcon.singleClickable {
                rightIcon()
            }
            typedArray.recycle()
        }
    }

    private fun arrowBack() {
        Toast.makeText(context, "Arrow Back", Toast.LENGTH_SHORT).show()
    }

    private fun rightIcon() {
        Toast.makeText(context, "Right Icon", Toast.LENGTH_SHORT).show()
    }
}