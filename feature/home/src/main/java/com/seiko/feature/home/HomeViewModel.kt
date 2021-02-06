package com.seiko.feature.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  pokemonListDataSource: HomeUiModelDataSource,
) : ViewModel() {

  val pokemonList: Flow<PagingData<HomeUiModel>>

  init {
    Timber.d("$this is created")

    pokemonList = Pager(config = PagingConfig(pageSize = 20)) {
      pokemonListDataSource
    }.flow
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }
}