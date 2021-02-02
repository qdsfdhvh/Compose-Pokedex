package com.seiko.base.initializer

import android.annotation.SuppressLint
import android.content.Context
import android.os.Process
import android.os.Process.THREAD_PRIORITY_FOREGROUND
import android.os.Process.THREAD_PRIORITY_LOWEST
import android.util.Log
import androidx.annotation.IntRange
import androidx.annotation.MainThread
import com.seiko.base.util.ProcessUtils
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set
import kotlin.reflect.KClass

private const val WAITING_TIME = 10000L

private typealias TaskKey = KClass<out TaskInterface>

class AppStartTaskDispatcher {

  private val startTaskList = ArrayList<TaskInterface>()
  private val taskHashMap = HashMap<TaskKey, TaskInterface>()
  private val taskChildHashMap = HashMap<TaskKey, MutableList<TaskKey>>()

  private lateinit var countDownLatch: CountDownLatch

  private val needWaitCount = AtomicInteger()

  private var startTime = 0L

  private var allTaskWaitTimeOut = 0L

  fun setShowLog(showLog: Boolean): AppStartTaskDispatcher {
    isShowLog = showLog
    return this
  }

  fun setAllTaskWaitTimeOut(timeOut: Long): AppStartTaskDispatcher {
    this.allTaskWaitTimeOut = timeOut
    return this
  }

  fun addAppStartTask(task: TaskInterface): AppStartTaskDispatcher {
    startTaskList.add(task)
    if (task.ifNeedWait()) {
      needWaitCount.getAndIncrement()
    }
    return this
  }

  fun addAppStartTasks(tasks: Collection<TaskInterface>): AppStartTaskDispatcher {
    tasks.forEach(::addAppStartTask)
    return this
  }

  @MainThread
  fun start(context: Context): AppStartTaskDispatcher {
    if (!ProcessUtils.isMainProcess(context)) {
      print("start() only run in main process")
      return this
    }
    startTime = System.currentTimeMillis()
    // 拓扑排序
    val sortTaskList = getSortResult()
    printSortTask(sortTaskList)
    countDownLatch = CountDownLatch(needWaitCount.get())
    dispatchAppStartTask(sortTaskList)
    return this
  }

  fun await() {
    check(this::countDownLatch.isInitialized) {
      "must run start() before await()"
    }
    if (allTaskWaitTimeOut <= 0L) {
      allTaskWaitTimeOut = WAITING_TIME
    }
    try {
      countDownLatch.await(allTaskWaitTimeOut, TimeUnit.MILLISECONDS)
    } catch (e: InterruptedException) {
      e.printStackTrace()
    }
    print("Finish await in Application, costTime:${System.currentTimeMillis() - startTime}ms")
  }

  // 分别处理主线程和子线程的任务
  private fun dispatchAppStartTask(sortTaskList: List<TaskInterface>) {
    val sortMainThreadTaskList = ArrayList<TaskInterface>(sortTaskList.size)
    sortTaskList.forEach { task ->
      if (task.isRunOnMainThread()) {
        sortMainThreadTaskList.add(task)
      } else {
        // 发送非主线程的任务
        task.runOnExecutor().execute(task.toProxy())
      }
    }
    // 再发送主线程的任务，防止主线程任务阻塞，导致子线程任务不能立刻执行
    sortMainThreadTaskList.forEach { task ->
      task.toProxy().run()
    }
  }

  /**
   * 拓扑排序
   * taskIntegerHashMap每个Task的入度
   * taskHashMap每个Task
   * taskChildHashMap每个Task的孩子
   * deque 入度为0的Task
   */
  private fun getSortResult(): List<TaskInterface> {
    val sortTaskList = ArrayList<TaskInterface>(startTaskList.size)
    val taskIntegerHashMap = HashMap<TaskKey, Int>()
    val deque = ArrayDeque<TaskKey>()

    for (task in startTaskList) {
      if (!taskIntegerHashMap.containsKey(task::class)) {
        taskHashMap[task::class] = task
        taskIntegerHashMap[task::class] = task.getDependsTaskList()?.size ?: 0
        taskChildHashMap[task::class] = ArrayList()
        //入度为0的队列
        if (taskIntegerHashMap[task::class] == 0) {
          deque.offer(task::class)
        }
      } else {
        throw java.lang.RuntimeException("任务重复了: " + task.javaClass)
      }
    }
    // 把孩子都加进去
    for (task in startTaskList) {
      if (task.getDependsTaskList() != null) {
        task.getDependsTaskList()?.forEach { dependsKClass ->
          taskChildHashMap[dependsKClass]?.add(task::class)
        }
      }
    }
    // 循环去除入度0的，再把孩子入度变成0的加进去
    while (!deque.isEmpty()) {
      val dependsKClass = deque.poll()!!
      sortTaskList.add(taskHashMap[dependsKClass]!!)
      for (classChild in taskChildHashMap[dependsKClass]!!) {
        taskIntegerHashMap[classChild] = taskIntegerHashMap[classChild]!! - 1
        if (taskIntegerHashMap[classChild] == 0) {
          deque.offer(classChild)
        }
      }
    }
    if (sortTaskList.size != startTaskList.size) {
      throw RuntimeException("出现环了")
    }
    return sortTaskList
  }

  private fun setNotifyChildren(task: TaskInterface) {
    taskChildHashMap[task::class]?.forEach { childTask ->
      taskHashMap[childTask]?.notifyNow()
    }
  }

  private fun markAppStartTaskFinish(task: TaskInterface) {
    if (task.ifNeedWait()) {
      countDownLatch.countDown()
      needWaitCount.getAndDecrement()
    }
  }

  private fun printSortTask(sortTaskList: List<TaskInterface>) {
    if (!isShowLog) return
    print(sortTaskList.joinToString(
      separator = "-->",
      prefix = "Task Sort："
    ) { it.javaClass.simpleName })
  }

  private fun TaskInterface.toProxy(): AppStartTaskProxy {
    return AppStartTaskProxy(this, this@AppStartTaskDispatcher)
  }

  private class AppStartTaskProxy(
    private val task: TaskInterface,
    private val appStartTaskDispatcher: AppStartTaskDispatcher,
  ) : Runnable {
    override fun run() {
      val start = System.currentTimeMillis()
      Process.setThreadPriority(task.priority())
      task.waitToNotify()
      task.run()
      appStartTaskDispatcher.setNotifyChildren(task)
      appStartTaskDispatcher.markAppStartTaskFinish(task)
      print("Finish Task: ${task.javaClass.simpleName}, costTime:${System.currentTimeMillis() - start}ms")
    }
  }

  companion object {
    private var isShowLog = false

    @SuppressLint("LogNotTimber")
    private fun print(msg: String) {
      if (isShowLog) {
        Log.i("AppStartTask", msg)
      }
    }
  }
}

interface TaskInterface : Runnable {

  @IntRange(from = THREAD_PRIORITY_FOREGROUND.toLong(), to = THREAD_PRIORITY_LOWEST.toLong())
  fun priority(): Int = Process.THREAD_PRIORITY_BACKGROUND

  fun getDependsTaskList(): List<KClass<out TaskInterface>>? = null

  fun needWait(): Boolean = false

  fun isRunOnMainThread(): Boolean = true

  fun runOnExecutor(): Executor

  fun waitToNotify()

  fun notifyNow()
}

private fun TaskInterface.ifNeedWait(): Boolean {
  return !isRunOnMainThread() && needWait()
}

abstract class AppStartTask : TaskInterface {
  private val depends by lazy {
    CountDownLatch(getDependsTaskList()?.size ?: 0)
  }

  override fun waitToNotify() {
    try {
      depends.await()
    } catch (e: InterruptedException) {
      e.printStackTrace()
    }
  }

  override fun notifyNow() {
    depends.countDown()
  }
}
