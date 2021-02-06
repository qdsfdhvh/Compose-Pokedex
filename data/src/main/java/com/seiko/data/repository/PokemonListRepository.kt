package com.seiko.data.repository

import com.seiko.data.network.PokedexClient
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
  private val client: PokedexClient,
) : Repository {

  suspend fun fetchPokemonList(page: Int) =
    client.fetchPokemonList(page)

}