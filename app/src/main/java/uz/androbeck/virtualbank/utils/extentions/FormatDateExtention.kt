package uz.androbeck.virtualbank.utils.extentions

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Long.formatToDayMonthYearHourMinute(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.formatToDayMonthYear(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(
        this
    )
}
fun Long.formatToHourMinute(): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(
        this
    )
}

