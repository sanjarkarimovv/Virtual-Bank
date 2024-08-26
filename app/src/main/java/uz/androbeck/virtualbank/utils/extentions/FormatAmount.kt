package uz.androbeck.virtualbank.utils.extentions

fun formatAmountWithSpaces(amount: Long): String {
    return amount.toString().reversed().chunked(3).joinToString(" ").reversed()
}
