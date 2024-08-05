package uz.androbeck.virtualbank.data.dto.request

@Serializable
data class SignInVerifyReqDto(
    @SerialName("token")
    val token: String? = null,
    @SerialName("code")
    val code: String? = null
) {
}