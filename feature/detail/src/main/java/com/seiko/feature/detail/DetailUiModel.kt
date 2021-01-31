package com.seiko.feature.detail

data class DetailUiModel(
  val pokemonName: String,
  val pokemonLogo: String,
  val pokemonWeight: String,
  val pokemonHeight: String,
  val pokemonHpProgress: Float,
  val pokemonHpLabel: String,
  val pokemonAtkProgress: Float,
  val pokemonAtkLabel: String,
  val pokemonDefenseProgress: Float,
  val pokemonDefenseLabel: String,
  val pokemonSpeedProgress: Float,
  val pokemonSpeedLabel: String,
  val pokemonExpProgress: Float,
  val pokemonExpLabel: String,
)