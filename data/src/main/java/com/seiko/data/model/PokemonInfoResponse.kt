package com.seiko.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoResponse(
  val id: Int,
  val name: String,
  val height: Int,
  val weight: Int,
  val experience: Int = 0,
  val types: List<TypeResponse>
)

@JsonClass(generateAdapter = true)
data class TypeResponse(
  val slot: Int,
  val type: Type
)

@JsonClass(generateAdapter = true)
data class Type(
  val name: String
)