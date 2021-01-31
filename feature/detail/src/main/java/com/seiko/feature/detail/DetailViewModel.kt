package com.seiko.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.seiko.common.compose.extensions.ComposeAssistedFactory
import com.seiko.data.repository.PokemonInfoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
  pokemonInfoRepository: PokemonInfoRepository,
  private val mapper: DetailUiModelMapper,
  @Assisted pokemonName: String,
) : ViewModel() {

  val pokemonInfo: LiveData<DetailUiModel>

  init {
    Timber.d("$this is created")

    pokemonInfo = pokemonInfoRepository.fetchPokemonInfo(pokemonName)
      .map { mapper.mapTo(it) }
      .catch { Timber.w(it) }
      .asLiveData()
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }

  @dagger.assisted.AssistedFactory
  interface AssistedFactory : ComposeAssistedFactory {
    fun create(pokemonName: String): DetailViewModel
  }
}