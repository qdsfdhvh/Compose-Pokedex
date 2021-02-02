package com.seiko.data.repository

import androidx.paging.PagingSource
import com.seiko.data.model.PokemonResponse
import com.seiko.data.network.PokedexClient
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
  private val client: PokedexClient,
) : PagingSource<Int, PokemonResponse>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResponse> {
    return try {
      val page = params.key ?: 0
      val response = client.fetchPokemonList(page)
      LoadResult.Page(
        data = response.results,
        prevKey = if (page == 0) null else page - 1,
        nextKey = page.plus(1)
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

}