package com.seiko.data.network

import com.seiko.data.model.PokemonInfoResponse
import com.seiko.data.model.PokemonResponse
import dagger.hilt.android.scopes.ViewModelScoped
import rxhttp.RxHttp
import rxhttp.await
import javax.inject.Inject

@ViewModelScoped
class PokedexClient @Inject constructor() {

  suspend fun fetchPokemonList(
    page: Int
  ) = RxHttp.get("pokemon")
    .add("offset", page * PAGE_SIZE)
    .add("limit", PAGE_SIZE)
    .await<PokemonResponse>()

  suspend fun fetchPokemonInfo(
    name: String
  ) = RxHttp.get("pokemon/$name")
    .await<PokemonInfoResponse>()

  companion object {
    private const val PAGE_SIZE = 20
  }
}