package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class HistoryLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HistoryLoadStateVH>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = HistoryLoadStateVH(parent, retry)

    override fun onBindViewHolder(
        holder: HistoryLoadStateVH,
        loadState: LoadState
    ) = holder.bind(loadState)
}