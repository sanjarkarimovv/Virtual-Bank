package uz.androbeck.virtualbank.ui.screens.add_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.androbeck.virtualbank.databinding.ItemAddCardImagesBinding

@Suppress("DEPRECATION")
class ViewPagerAdapter(
    val cardStyleImages: List<Int>,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(
        val binding: ItemAddCardImagesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                iv.load(cardStyleImages[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemAddCardImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cardStyleImages.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind()
    }
}