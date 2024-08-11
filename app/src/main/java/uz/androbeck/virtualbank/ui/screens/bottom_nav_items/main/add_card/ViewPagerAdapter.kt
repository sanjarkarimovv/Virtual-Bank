package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.add_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemAddCardImagesBinding

class ViewPagerAdapter(
    val images: List<Int>,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(
        val binding: ItemAddCardImagesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                iv.setImageResource(images[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemAddCardImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind()
    }
}