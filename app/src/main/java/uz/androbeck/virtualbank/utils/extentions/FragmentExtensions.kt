package uz.androbeck.virtualbank.utils.extentions

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.color(@ColorRes colorRes: Int) = requireContext().color(colorRes)

fun Fragment.colorStateList(@ColorRes colorRes: Int) = requireContext().colorStateList(colorRes)

fun Fragment.drawable(@DrawableRes drawableRes: Int) = requireContext().drawable(drawableRes)

fun Fragment.dimension(@DimenRes dimenRes: Int) = requireContext().dimension(dimenRes)

fun Fragment.dimensionInt(@DimenRes dimenRes: Int) = requireContext().dimensionInt(dimenRes)

fun Fragment.toast(message: String, isLong: Boolean = false) {
    requireContext().toast(message, isLong)
}