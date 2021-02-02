package com.seiko.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
  private val dataSource: PokemonListDataSource,
) : Repository {

  fun fetchPokemonList() =
    Pager(
      config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
      )
    ) { dataSource }.flow

}