package com.seiko.base

import android.app.Application
import com.seiko.base.initializer.AppInitializer
import com.seiko.base.initializer.AppStartTaskDispatcher
import com.seiko.base.util.ProcessUtils
import javax.inject.Inject

abstract class BaseApplication : Application() {

  @Inject
  lateinit var initializers: Set<@JvmSuppressWildcards AppInitializer>

  override fun onCreate() {
    super.onCreate()
    // 只在主进程运行
    if (ProcessUtils.isMainProcess(this)) {
      initAppStartTask()
    }
  }

  private fun initAppStartTask() {
    AppStartTaskDispatcher()
      .setShowLog(BuildConfig.DEBUG)
      .setAllTaskWaitTimeOut(1000)
      .addAppStartTasks(initializers)
      .start(this)
      .await()
  }

}