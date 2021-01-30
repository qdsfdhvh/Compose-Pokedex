package com.seiko.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<PokemonResponse>,
)

@JsonClass(generateAdapter = true)
data class PokemonResponse(
  val page: Int = 0,
  val name: String,
  val url: String,
)