package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.test.ADD_HOME
import uz.androbeck.test.Model
import uz.androbeck.virtualbank.databinding.ItemMyHomeBinding

class MyHomeAdapter : ListAdapter<Model, MyHomeAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemMyHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Model) {
            with(binding) {
                model.title?.let { tvTitle.text = it }
                if (model.title == ADD_HOME) {
                    imAdd.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder(
            ItemMyHomeBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}