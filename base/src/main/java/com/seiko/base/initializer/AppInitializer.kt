package com.seiko.base.initializer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import java.util.concurrent.Executor
import kotlin.reflect.KClass

abstract class AppInitializer : AppStartTask() {

  override fun isRunOnMainThread(): Boolean {
    return true
  }

  override fun getDependsTaskList(): List<KClass<out TaskInterface>>? {
    return listOf(TimberInitializer::class)
  }

  override fun runOnExecutor(): Executor {
    return Dispatchers.IO.asExecutor()
  }
}