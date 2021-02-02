package com.seiko.base.initializer

import com.seiko.base.BuildConfig
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * 初始化日志
 */
class TimberInitializer @Inject constructor() : AppInitializer() {

  override fun run() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  override fun getDependsTaskList(): List<KClass<out TaskInterface>>? {
    return null
  }
}