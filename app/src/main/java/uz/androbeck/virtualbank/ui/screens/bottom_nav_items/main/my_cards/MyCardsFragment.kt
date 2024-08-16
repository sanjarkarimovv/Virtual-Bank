package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMyCardsBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.toast

class MyCardsFragment : BaseFragment(R.layout.fragment_my_cards) {

    private val binding by viewBinding(FragmentMyCardsBinding::bind)
    private lateinit var pagingAdapter: ViewPagerAdapter
    override fun setup() {

        pagingAdapter = ViewPagerAdapter()
        pagingAdapter.load(loadList())
        binding.viewPager.adapter = pagingAdapter


        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.setText("Tab ${position + 1}")
        }.attach()

    }

    override fun clicks() {
        binding.btnAddCard.setOnClickListener {
            toast("bbbb")
            findNavController().navigate(R.id.action_myCardsFragment_to_addCardFragment)
        }
    }

    private fun loadList() = listOf(
        "Card 1",
        "Card 2",
        "Card 3",
        "Card 4",
        "Card 5",
        "Card 6",
    )
}