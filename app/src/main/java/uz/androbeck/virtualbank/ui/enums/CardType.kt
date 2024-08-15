package uz.androbeck.virtualbank.ui.enums

enum class CardType(
    val typeCodes: List<String>,
) {
    UZCARD(listOf("8600", "5614")),
    HUMO(listOf("9860")),
    UNKNOWN(listOf(""));

    companion object {
        fun fromCardNumber(cardNumber: String): CardType {
            return entries.firstOrNull { cardNumber in it.typeCodes } ?: UNKNOWN
        }
    }
}