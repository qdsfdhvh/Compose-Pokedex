package com.seiko.feature.home

import com.seiko.base.mapper.BeanMapper
import com.seiko.data.model.PokemonResponse
import javax.inject.Inject

class HomeUiModelMapper @Inject constructor() : BeanMapper<PokemonResponse, HomeUiModel> {
  override fun mapTo(model: PokemonResponse): HomeUiModel {
    return HomeUiModel(
      pokemonName = model.name,
      pokemonLogo = model.url.split("/".toRegex())
        .dropLast(1)
        .last()
        .let { "https://pokeres.bastionbot.org/images/pokemon/$it.png" }
    )
  }
}