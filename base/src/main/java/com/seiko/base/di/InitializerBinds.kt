package com.seiko.base.di

import com.seiko.base.initializer.AppInitializer
import com.seiko.base.initializer.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
interface InitializerBinds {

  @Binds
  @IntoSet
  fun bindTimberInitializer(initializer: TimberInitializer): AppInitializer

}