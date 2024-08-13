package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.response.home.TotalBalanceResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.home.TotalBalanceUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TotalBalanceMapper @Inject constructor() :
    BaseMapper<TotalBalanceResDto, TotalBalanceUIModel> {
    override fun toUIModel(dto: TotalBalanceResDto) =dto.run {
        TotalBalanceUIModel(
            totalBalance = dto.total_balance
        )
    }

    override fun toDTO(uiModel: TotalBalanceUIModel) =uiModel.run {
        TotalBalanceResDto(
            total_balance = uiModel.totalBalance
        )
    }
}