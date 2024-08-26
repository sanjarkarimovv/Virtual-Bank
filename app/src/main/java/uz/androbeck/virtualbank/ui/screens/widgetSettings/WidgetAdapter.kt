package uz.androbeck.virtualbank.ui.screens.widgetSettings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemWidgetSettingsBinding
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents

class WidgetAdapter(
    private val action: (item: UiComponents?) -> Unit
) : ListAdapter<UiComponents, WidgetAdapter.WidgetHolder>(diffUtil) {

    inner class WidgetHolder(private val binding: ItemWidgetSettingsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiComponents?) = with(binding) {
            item?.run {
                tvItemName.text = value
                if (!isShow) {
                    btnCancel.rotation = 0f
                }
            }
            binding.root.setOnClickListener {
                action.invoke(item)
            }
        }
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UiComponents>() {
            override fun areItemsTheSame(oldItem: UiComponents, newItem: UiComponents): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UiComponents, newItem: UiComponents): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetHolder {
        return WidgetHolder(
            ItemWidgetSettingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WidgetHolder, position: Int) {
        holder.bind(getItem(position))
    }

}