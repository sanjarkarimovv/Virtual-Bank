package uz.androbeck.virtualbank.ui.resource

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.drawable

class ResProviderImpl(private val context: Context) : ResProvider {

    override fun getColor(@ColorRes id: Int) = context.color(id)

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg args: Any) = context.getString(id, args)

    override fun getDrawable(@DrawableRes id: Int) = context.drawable(id)
}
