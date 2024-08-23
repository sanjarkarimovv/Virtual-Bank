package uz.androbeck.virtualbank.ui.screens.widgetSettings

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouch<T : RecyclerView.Adapter<*>>(
    private val adapter: T
) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (adapter is WidgetAdapter) {
            adapter.getList().let {
                val from = viewHolder.layoutPosition
                val to = target.layoutPosition
                val temp = it[from]
                it[from] = it[to]
                it[to] = temp
                adapter.submitList(it)
            }
        }
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }
}