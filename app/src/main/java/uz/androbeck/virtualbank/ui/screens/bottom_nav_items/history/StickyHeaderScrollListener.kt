package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.utils.extentions.formatToDayMonthYear

class StickyHeaderScrollListener(
    private val recyclerView: RecyclerView,
    private val adapter: HistoryAdapter,
    private val stickyHeaderView: View
) : RecyclerView.OnScrollListener() {

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (firstVisiblePosition == RecyclerView.NO_POSITION) return

        val currentItem = adapter.getItemAt(firstVisiblePosition)

        if (currentItem is HistoryItem.Content) {
            val headerPosition = findHeaderPositionForItem(firstVisiblePosition)
            if (headerPosition >= 0) {
                val header = adapter.getItemAt(headerPosition) as? HistoryItem.Header
                updateStickyHeaderUI(header)
            }
        }
    }

    private fun findHeaderPositionForItem(itemPosition: Int): Int {
        for (position in itemPosition downTo 0) {
            val item = adapter.getItemAt(position)
            if (item is HistoryItem.Header) {
                return position
            }
        }
        return -1
    }

    private fun updateStickyHeaderUI(header: HistoryItem.Header?) {
        // Предположим, что stickyHeaderView это TextView
        (stickyHeaderView as? TextView)?.text = header?.time?.formatToDayMonthYear() ?: ""
    }
}
