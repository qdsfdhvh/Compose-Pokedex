package com.seiko.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.seiko.data.repository.PokemonInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val pokemonInfoRepository: PokemonInfoRepository,
  private val mapper: DetailUiModelMapper,
) : ViewModel() {

  init {
    Timber.d("$this is created")
  }

  fun fetch(pokemonName: String): LiveData<DetailUiModel> {
    return pokemonInfoRepository.fetchPokemonInfo(pokemonName)
      .map { mapper.mapTo(it) }
      .catch { Timber.w(it) }
      .asLiveData()
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }

}