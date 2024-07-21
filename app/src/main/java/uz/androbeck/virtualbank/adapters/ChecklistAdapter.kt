package uz.androbeck.virtualbank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R

class ChecklistAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {
    private val checkedItems = mutableListOf<Int>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.checklist_checkbox)
        val checkBoxText: TextView = itemView.findViewById(R.id.checklist_text)
        val checkBoxOption: TextView = itemView.findViewById(R.id.checklist_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_checklist_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    val option= listOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.checkBoxText.text = item
        holder.checkBoxOption.text = item
        holder.checkBoxOption.text= (option[position])
        holder.checkbox.isChecked = checkedItems.contains(position)
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedItems.add(position)
            } else {
                checkedItems.remove(position)
            }
        }
    }
    fun getCheckedItems(): MutableList<Int> = checkedItems
}