package uz.androbeck.virtualbank.domain.ui_models.home

import java.io.Serializable

data class FullInfoUIModel(
    val phone: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val bornDate: String? = null,
    val gender: String? = null
) : Serializable