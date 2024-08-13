package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import StickyHeaderDecoration
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentHistoryBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
@AndroidEntryPoint
class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val binding: FragmentHistoryBinding by viewBinding()
    private val viewModel: HistoryViewModelPaging by viewModels()
    private lateinit var historyAdapter: HistoryAdapter
    private var isScrollViewVisible = true
    override fun setup() {
        historyAdapter = HistoryAdapter { child ->
            // RV elematlariga bosganda...
        }
        binding.recyclerView.apply {
            adapter = historyAdapter
            addItemDecoration(StickyHeaderDecoration(historyAdapter))
        }

//        viewModel.historyItems.observe(viewLifecycleOwner) { items ->
//            historyAdapter.submitList(items)
//        }

        lifecycleScope.launch {
            viewModel.historyItems.collectLatest { pagingData ->
                historyAdapter.submitData(pagingData)
            }
        }

    }
}