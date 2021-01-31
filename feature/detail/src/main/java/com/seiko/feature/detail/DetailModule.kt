package com.seiko.feature.detail

import com.seiko.common.compose.extensions.ComposeAssistedFactory
import com.seiko.common.compose.extensions.ComposeAssistedFactoryKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@InstallIn(SingletonComponent::class)
@Module
interface DetailModule {

  @Binds
  @IntoMap
  @ComposeAssistedFactoryKey(DetailViewModel.AssistedFactory::class)
  fun bindFactory(factory: DetailViewModel.AssistedFactory): ComposeAssistedFactory

}