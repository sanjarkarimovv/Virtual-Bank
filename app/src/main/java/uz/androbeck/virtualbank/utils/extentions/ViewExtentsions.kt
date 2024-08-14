package uz.androbeck.virtualbank.utils.extentions

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.dpToPxFloat(dp: Float): Float = context.dpToPxFloat(dp)

fun View.dpToPx(dp: Int): Int = context.dpToPx(dp)

fun View.pxToDp(px: Int): Int = context.pxToDp(px)

