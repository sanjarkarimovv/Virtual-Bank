package uz.androbeck.virtualbank.ui.customViews.forHome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.databinding.ItemAdvertisingBinding
import uz.androbeck.virtualbank.domain.ui_models.home.AdvertisingModel

class AdvertisingAdapter(
    private val list: List<AdvertisingModel>
) : RecyclerView.Adapter<AdvertisingAdapter.AdvertisingHolder>() {
    inner class AdvertisingHolder(private val binding: ItemAdvertisingBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(advertisingModel: AdvertisingModel) = with(binding){
                bgImage.setImageResource(advertisingModel.icon)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisingHolder {
        return AdvertisingHolder(
            ItemAdvertisingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdvertisingHolder, position: Int) {
        holder.bind(list[position])
    }
}
