package uz.androbeck.virtualbank.utils.extentions

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun TextView.setHtmlText(htmlText: String?) {
    text = when {
        htmlText.isNullOrEmpty() -> {
            ""
        }

        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        }

        else -> {
            @Suppress("DEPRECATION")
            Html.fromHtml(htmlText)
        }
    }
}

fun TextView.setTextColorRes(@ColorRes colorResId: Int) {
    setTextColor(ContextCompat.getColor(context, colorResId))
}

fun TextView.setDrawableStart(@DrawableRes startDrawableResId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
        context.getDrawable(startDrawableResId),
        null,
        null,
        null
    )
}

fun TextView.setDrawableEnd(@DrawableRes endDrawableResId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
        null,
        null,
        context.getDrawable(endDrawableResId),
        null
    )
}

fun TextView.setDrawableStart(
    @DrawableRes startDrawableResId: Int,
    width: Int,
    height: Int
) {
    val drawableStart = context.getDrawable(startDrawableResId) ?: return

    drawableStart.setBounds(0, 0, width, height)
    setCompoundDrawables(
        drawableStart,
        null,
        null,
        null
    )
}

fun TextView.removeDrawables() {
    setCompoundDrawablesWithIntrinsicBounds(
        null,
        null,
        null,
        null
    )
}
