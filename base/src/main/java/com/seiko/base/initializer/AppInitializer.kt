package com.seiko.base.initializer

import com.wxy.appstartfaster.task.AppStartTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import java.util.concurrent.Executor

abstract class AppInitializer : AppStartTask() {

  /**
   * 是否运行在主线程
   */
  override fun isRunOnMainThread(): Boolean {
    return true
  }

  final override fun getDependsTaskList(): List<Class<out AppStartTask>> {
    return getDependsInitialList()
  }

  open fun getDependsInitialList(): List<Class<out AppInitializer>> {
    return listOf(TimberInitializer::class.java)
  }

  override fun runOnExecutor(): Executor {
    return Dispatchers.IO.asExecutor()
  }
}