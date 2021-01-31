package com.seiko.data.network

import com.seiko.data.model.PokemonInfoResponse
import com.seiko.data.model.PokemonListResponse
import rxhttp.RxHttp
import rxhttp.await
import javax.inject.Inject

class PokedexClient @Inject constructor() {

  suspend fun fetchPokemonList(
    page: Int
  ) = RxHttp.get("pokemon")
    .add("offset", page * PAGE_SIZE)
    .add("limit", PAGE_SIZE)
    .await<PokemonListResponse>()

  suspend fun fetchPokemonInfo(
    name: String
  ) = RxHttp.get("pokemon/$name")
    .await<PokemonInfoResponse>()

  companion object {
    private const val PAGE_SIZE = 20
  }
}