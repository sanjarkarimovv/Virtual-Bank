@file:Suppress("DEPRECATION")

package uz.androbeck.virtualbank.utils.extentions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.inputmethod.InputMethodManager
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

//vibration extension for events
fun Fragment.vibrate() {
    val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator.takeIf { it.hasVibrator() }?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            it.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            it.vibrate(50)
        }
    }

}

fun Fragment.dpToPx(dp: Float): Int = requireContext().dpToPx(dp)

fun Fragment.dpToPxFloat(dp: Float): Float = requireContext().dpToPxFloat(dp)

fun Fragment.dpToPx(dp: Int): Int = requireContext().dpToPx(dp)

fun Fragment.pxToDp(px: Int): Int = requireContext().pxToDp(px)

fun Fragment.hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
}