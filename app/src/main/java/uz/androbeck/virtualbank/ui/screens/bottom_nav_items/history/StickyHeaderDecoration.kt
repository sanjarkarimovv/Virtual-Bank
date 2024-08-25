package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class StickyHeaderDecoration(private val adapter: HistoryAdapter) : RecyclerView.ItemDecoration() {

    private var currentHeader: View? = null

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var nextHeader: View? = null
        var nextHeaderTop: Int = Int.MAX_VALUE

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            val header = adapter.findHeaderViewForPosition1(position, parent)

            if (header != null) {
                if (currentHeader == null) {
                    currentHeader = header
                    fixLayoutSize(parent, header)
                } else if (header !== currentHeader) {
                    if (child.top < nextHeaderTop) {
                        nextHeader = header
                        nextHeaderTop = child.top
                    }
                }
            }
        }
        if (nextHeader != null && nextHeaderTop <= currentHeader!!.height) {
            // Здесь происходит изменение заголовка
            currentHeader = nextHeader
            fixLayoutSize(parent, currentHeader!!)
        }


        if (currentHeader != null) {
            c.save()
            if (nextHeader != null && nextHeaderTop <= currentHeader!!.height) {

                val translationY = (nextHeaderTop - currentHeader!!.height).toFloat()
                println("uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history.StickyHeaderDecoration Translation Y: $translationY")
                println("uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history.StickyHeaderDecoration Current Header: ${currentHeader}, Next Header Top: $nextHeader")
                c.translate(0f, translationY)
            }
            currentHeader!!.draw(c)
            c.restore()
        }
    }

    private fun fixLayoutSize(parent: RecyclerView, view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )

        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val header = adapter.findHeaderViewForPosition1(position, parent)
        if (header != null) {
            outRect.top = header.height
        } else {
            outRect.top = 0
        }
    }
}
