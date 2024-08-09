package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import StickyHeaderDecoration
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentHistoryBinding
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val binding: FragmentHistoryBinding by viewBinding()
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter
    override fun setup() {
        historyAdapter = HistoryAdapter { child ->
            // RV elematlariga bosganda...
        }
        binding.recyclerView.apply {
            adapter = historyAdapter
            addItemDecoration(StickyHeaderDecoration(historyAdapter))
        }

        viewModel.historyItems.observe(viewLifecycleOwner) { items ->
            historyAdapter.submitList(items)
        }

    }
}