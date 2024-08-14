package uz.androbeck.virtualbank.ui.customViews.forHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemLastTransferBinding
import uz.androbeck.virtualbank.domain.ui_models.home.LastTransferModel

class LastTransferAdapter : ListAdapter<LastTransferModel, LastTransferAdapter.LastTransferHolder>(diffUtil) {
    inner class LastTransferHolder(private val binding: ItemLastTransferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lastTransferModel: LastTransferModel) = with(binding) {
            lastTransferModel.run {

                // ...
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LastTransferModel>() {
            override fun areItemsTheSame(oldItem: LastTransferModel, newItem: LastTransferModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LastTransferModel, newItem: LastTransferModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastTransferHolder {
        return LastTransferHolder(
            ItemLastTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LastTransferHolder, position: Int) {
        holder.bind(getItem(position))
    }
}