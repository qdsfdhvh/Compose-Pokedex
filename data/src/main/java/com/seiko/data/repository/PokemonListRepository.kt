package com.seiko.data.repository

import androidx.annotation.WorkerThread
import com.seiko.data.network.PokedexClient
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
  private val client: PokedexClient
) : Repository {

  @WorkerThread
  fun fetchPokemonList(
    page: Int
  ) = flow {
    val response = client.fetchPokemonList(page)
    emit(response.results)
  }

}