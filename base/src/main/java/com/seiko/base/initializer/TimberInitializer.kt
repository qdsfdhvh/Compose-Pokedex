package com.seiko.base.initializer

import com.seiko.base.BuildConfig
import timber.log.Timber
import javax.inject.Inject

/**
 * 初始化日志
 */
class TimberInitializer @Inject constructor() : AppInitializer() {

  override fun run() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  override fun getDependsInitialList(): List<Class<out AppInitializer>> {
    return emptyList()
  }
}