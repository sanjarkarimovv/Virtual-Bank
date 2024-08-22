package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ItemLoadFooterBinding

class HistoryLoadStateVH(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.item_load_footer, parent, false)
) {
    private val binding = ItemLoadFooterBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.progressBar
    private val retry: Button = binding.btnRefresh
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {
//       if (loadState is LoadState.Error) {
//            binding.tvErrorMsg.text = loadState.error.localizedMessage
//        }

        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
       // binding.tvErrorMsg.isVisible = loadState is LoadState.Error
    }
}