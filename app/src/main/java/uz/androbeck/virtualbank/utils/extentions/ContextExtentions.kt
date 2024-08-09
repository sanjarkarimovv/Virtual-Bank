package uz.androbeck.virtualbank.utils.extentions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.colorStateList(@ColorRes colorRes: Int) =
    ContextCompat.getColorStateList(this, colorRes)

fun Context.drawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

fun Context.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun Context.dimensionInt(@DimenRes dimenRes: Int) = resources.getDimensionPixelOffset(dimenRes)

fun Context.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun Context.dpToPxFloat(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.dpToPx(dp: Int): Int = dpToPx(dp.toFloat())

fun Context.pxToDp(px: Int): Int = (px / resources.displayMetrics.density).toInt()
