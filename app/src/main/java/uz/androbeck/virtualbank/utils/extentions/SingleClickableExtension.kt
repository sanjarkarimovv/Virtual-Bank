package uz.androbeck.virtualbank.utils.extentions

import android.view.View

fun View.singleClickable(l: View.OnClickListener) {
    setOnClickListener(SingleClickListener(l))
}

internal class SingleClickListener(private inline val clickListener: View.OnClickListener) :
    View.OnClickListener {

    private var lastClick: Long = 0

    override fun onClick(v: View) {
        if (getLastClickTimeout() > DOUBLE_CLICK_TIMEOUT) {
            lastClick = System.currentTimeMillis()
            clickListener.onClick(v)
        }
    }

    private fun getLastClickTimeout() = System.currentTimeMillis() - lastClick

    companion object {

        private const val DOUBLE_CLICK_TIMEOUT = 500
    }
}
