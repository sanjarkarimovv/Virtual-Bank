package uz.androbeck.virtualbank.ui.enums

enum class PhoneNumberType(
    val typeCodes: List<String>,
) {
    UzMobile(listOf("99895", "99899")),
    Beeline(listOf("99890", "99891")),
    Ucell(listOf("99893", "99894","99850")),
    Mobiuz(listOf("99888", "99897")),
    Humons(listOf("99833")),
    UNKNOWN(listOf(""));

    companion object {
        fun fromPhoneNumber(phoneNumber: String): PhoneNumberType {
            return entries.firstOrNull { phoneNumber in it.typeCodes } ?: UNKNOWN
        }
    }
}