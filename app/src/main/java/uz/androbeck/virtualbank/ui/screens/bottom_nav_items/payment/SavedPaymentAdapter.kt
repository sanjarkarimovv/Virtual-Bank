package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.payment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.test.ADD_HOME
import uz.androbeck.test.Model
import uz.androbeck.virtualbank.databinding.ItemSavedPaymentsBinding

class SavedPaymentAdapter : ListAdapter<Model, SavedPaymentAdapter.SavedViewHolder>(diffUtil) {

    inner class SavedViewHolder(
        private val binding: ItemSavedPaymentsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Model) {
            with(binding) {
                model.logo?.let { ivLogo.setImageResource(it) }
                model.title?.let { tvTitle.text = it }
                model.phoneNumber?.let { tvPhoneNumber.text = it }
                if (model.title == ADD_HOME){
                    mainSave.visibility = View.GONE
                    btnAdd.visibility = View.VISIBLE
                }else{
                    mainSave.visibility = View.VISIBLE
                    btnAdd.visibility = View.GONE
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
            ItemSavedPaymentsBinding.inflate(
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