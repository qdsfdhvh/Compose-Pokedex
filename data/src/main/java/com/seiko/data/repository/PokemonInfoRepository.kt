package com.seiko.data.repository

import androidx.annotation.WorkerThread
import com.seiko.data.network.PokedexClient
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonInfoRepository @Inject constructor(
  private val client: PokedexClient
) : Repository {

  @WorkerThread
  fun fetchPokemonInfo(
    name: String
  ) = flow {
    val response = client.fetchPokemonInfo(name)
    emit(response)
  }

}