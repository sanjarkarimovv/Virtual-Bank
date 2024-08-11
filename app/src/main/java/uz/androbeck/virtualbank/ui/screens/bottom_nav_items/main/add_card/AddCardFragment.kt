package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.add_card

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentAddCardBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

class AddCardFragment : BaseFragment(R.layout.fragment_add_card) {

    private val binding by viewBinding(FragmentAddCardBinding::bind)

    @SuppressLint("InflateParams")
    override fun setup() {
        val images = listOf(
            R.drawable.card_image1,
            R.drawable.card_image2,
            R.drawable.card_image3,
            R.drawable.card_image4,
            R.drawable.card_image5,
            R.drawable.card_image6,
            R.drawable.card_image1,
            R.drawable.card_image2,
            R.drawable.card_image3,
            R.drawable.card_image4,
            R.drawable.card_image5,
            R.drawable.card_image6,
        )
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(images)

            viewPager.apply {
                offscreenPageLimit = 1

                val recyclerView = getChildAt(0) as RecyclerView

                recyclerView.apply {
                    val padding = resources.getDimensionPixelOffset(R.dimen.halfPageMargin) +
                            resources.getDimensionPixelOffset(R.dimen.peekOffset)
                    setPadding(padding, 0, padding, 0)
                    clipToPadding = false
                }
            }

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
                val customView: View =
                    LayoutInflater.from(requireContext()).inflate(R.layout.item_tab, null)
                val tabIcon = customView.findViewById<ImageView>(R.id.tab_icon)
                val tabCheckIcon = customView.findViewById<ImageView>(R.id.check_icon)

                tabIcon.setImageResource(images[position])
                tab.customView = customView
                tabCheckIcon.visibility = View.GONE
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val checkIcon = tab?.customView?.findViewById<ImageView>(R.id.check_icon)
                    checkIcon?.visibility = View.VISIBLE
                    val icon = tab?.customView?.findViewById<ImageView>(R.id.tab_icon)
                    icon?.animate()?.scaleX(1.5f)?.scaleY(1.5f)?.setDuration(300)?.start()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val checkIcon = tab?.customView?.findViewById<ImageView>(R.id.check_icon)
                    checkIcon?.visibility = View.GONE
                    val icon = tab?.customView?.findViewById<ImageView>(R.id.tab_icon)
                    icon?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(300)?.start()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
}