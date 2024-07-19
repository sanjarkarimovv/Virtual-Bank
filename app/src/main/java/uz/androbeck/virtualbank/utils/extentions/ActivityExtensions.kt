package uz.androbeck.virtualbank.utils.extentions

import android.app.Activity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Activity.color(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Activity.colorStateList(@ColorRes colorRes: Int) =
    ContextCompat.getColorStateList(this, colorRes)

fun Activity.drawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

fun Activity.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun Activity.dimensionInt(@DimenRes dimenRes: Int) = resources.getDimensionPixelOffset(dimenRes)

fun Activity.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}