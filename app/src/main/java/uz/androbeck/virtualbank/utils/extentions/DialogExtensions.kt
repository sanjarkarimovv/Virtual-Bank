package uz.androbeck.virtualbank.utils.extentions

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.adapters.ChecklistAdapter

fun Context.simpleDialog(
    icon: Int? =null,
    title: String,
    message: String,
    action1: (() -> Unit)? =null,
    action2: (() -> Unit)? =null,
){
    val dialog =Dialog(this)
    val view =LayoutInflater.from(this).inflate(R.layout.fragment_simple_dialog,null)
    dialog.setContentView(view)

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window?.attributes?.windowAnimations = com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation

    val iconImageView =view.findViewById<ImageView>(R.id.dialog_icon)
    val titleTextView =view.findViewById<TextView>(R.id.dialog_title)
    val messageTextView =view.findViewById<TextView>(R.id.dialog_message)
    val action1TextView =view.findViewById<TextView>(R.id.dialog_action1)
    val action2TextView =view.findViewById<TextView>(R.id.dialog_action2)

    if (icon!=null){
        iconImageView.setImageResource(icon)
    }
    else{
        iconImageView.visibility= View.GONE
    }
    icon?.let { iconImageView.background=getDrawable(it) }
    title.let { titleTextView.text =it }
    message.let { messageTextView.text =message }

    action1TextView.setOnClickListener {
        action1?.invoke()
        dialog.dismiss()
    }
    action2TextView.setOnClickListener {
        action2?.invoke()
        dialog.dismiss()
    }
    dialog.show()
}

fun Context.optionDialog(
    title: String,
    message: String,
    checklistItems: List<String>,
    icon: Int? =null,
    action1: ((MutableList<Int>) -> Unit)? =null,
    action2: (() -> Unit)? =null,
){
    val dialog =Dialog(this)
    val view =LayoutInflater.from(this).inflate(R.layout.fragment_option_dialog,null)
    dialog.setContentView(view)

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window?.attributes?.windowAnimations = com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation

    val iconImageView =view.findViewById<ImageView>(R.id.dialog2_icon)
    val titleTextView =view.findViewById<TextView>(R.id.dialog2_title)
    val messageTextView =view.findViewById<TextView>(R.id.dialog2_message)
    val action1TextView =view.findViewById<TextView>(R.id.dialog2_action1)
    val action2TextView =view.findViewById<TextView>(R.id.dialog2_action2)
    val checklist=view.findViewById<RecyclerView>(R.id.dialog_checklist)
    checklist.layoutManager= LinearLayoutManager(this)
    val adapter= ChecklistAdapter(checklistItems)
    checklist.adapter=adapter
    if (icon!=null){
        iconImageView.setImageResource(icon)
    }
    else{
        iconImageView.visibility= View.GONE
    }
    icon?.let { iconImageView.background=getDrawable(it) }
    title.let { titleTextView.text =it }
    message.let { messageTextView.text =message }

    action1TextView.setOnClickListener {
        action1?.invoke(adapter.getCheckedItems())
        dialog.dismiss()
    }
    action2TextView.setOnClickListener {
        action2?.invoke()
        dialog.dismiss()
    }
    dialog.show()

}