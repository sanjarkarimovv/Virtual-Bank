import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history.HistoryAdapter

class StickyHeaderDecoration(private val adapter: HistoryAdapter) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var currentHeader: View? = null
        var nextHeader: View? = null
        var nextHeaderTop: Int = 0

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            val header = adapter.findHeaderViewForPosition1(position, parent)

            if (header != null) {
                if (currentHeader == null) {
                    currentHeader = header
                    fixLayoutSize(parent, header)
                } else if (header !== currentHeader) {
                    nextHeader = header
                    nextHeaderTop = child.top
                    println("StickyHeaderDecoration Current Header: ${currentHeader}, Next Header Top: $nextHeader")
                    break
                }
            }
        }

        if (currentHeader != null) {
            c.save()
            c.translate(0f, 0f)
            if (nextHeader != null && nextHeaderTop <= currentHeader.height) {
                val translationY = (nextHeaderTop - currentHeader.height).toFloat()
                c.translate(0f, translationY)
            }

            currentHeader.draw(c)
            c.restore()
        }
    }

    private fun fixLayoutSize(parent: RecyclerView, view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight, view.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom, view.layoutParams.height
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
