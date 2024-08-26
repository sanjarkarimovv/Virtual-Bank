package uz.androbeck.virtualbank.ui.customViews.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import uz.androbeck.virtualbank.databinding.CustomToolbarForHomeBinding

class CustomToolbarForHome @JvmOverloads(
) constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {
    private val binding =
        CustomToolbarForHomeBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        binding.btnNotification.setOnClickListener {
            clickNotification?.invoke()
        }
    }

    var clickNotification: (() -> Unit)? = null


}