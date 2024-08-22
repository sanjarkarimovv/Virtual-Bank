package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComponentsMapper @Inject constructor() : BaseMapper<HomeEntity, UiComponents> {
    override fun toUIModel(dto: HomeEntity) = dto.run {
        UiComponents(
            id = id,
            name = name,
            isShow = isShow,
            value = value
        )
    }

    override fun toDTO(uiModel: UiComponents) = uiModel.run {
        HomeEntity(
            name = name,
            isShow = isShow,
            value = value
        )
    }
}