package com.seiko.feature.detail

import com.seiko.base.mapper.BeanMapper
import com.seiko.data.model.PokemonInfoResponse
import javax.inject.Inject
import kotlin.random.Random

class DetailUiModelMapper @Inject constructor() : BeanMapper<PokemonInfoResponse, DetailUiModel> {

  private val random = Random.Default

  override fun mapTo(model: PokemonInfoResponse): DetailUiModel {
    val hp = random.nextInt(MAX_HP)
    val atk = random.nextInt(MAX_ATTACK)
    val defense = random.nextInt(MAX_DEFENSE)
    val speed = random.nextInt(MAX_SPEED)
    val exp = random.nextInt(MAX_EXP)
    return DetailUiModel(
      pokemonName = model.name,
      pokemonLogo = "https://pokeres.bastionbot.org/images/pokemon/${model.id}.png",
      pokemonWeight = String.format("%.1f KG", model.weight.toFloat() / 10),
      pokemonHeight = String.format("%.1f M", model.height.toFloat() / 10),
      pokemonHpProgress = hp.toFloat() / MAX_HP,
      pokemonHpLabel = "$hp/$MAX_HP",
      pokemonAtkProgress = atk.toFloat() / MAX_ATTACK,
      pokemonAtkLabel = "$atk/$MAX_ATTACK",
      pokemonDefenseProgress = defense.toFloat() / MAX_DEFENSE,
      pokemonDefenseLabel = "$defense/$MAX_DEFENSE",
      pokemonSpeedProgress = speed.toFloat() / MAX_SPEED,
      pokemonSpeedLabel = "$speed/$MAX_SPEED",
      pokemonExpProgress = exp.toFloat() / MAX_EXP,
      pokemonExpLabel = "$exp/$MAX_EXP",
    )
  }

  companion object {
    private const val MAX_HP = 300
    private const val MAX_ATTACK = 300
    private const val MAX_DEFENSE = 300
    private const val MAX_SPEED = 300
    private const val MAX_EXP = 1000
  }
}