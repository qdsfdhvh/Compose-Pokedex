package com.seiko.data.initializer

import com.seiko.base.initializer.AppInitializer
import com.seiko.data.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Lazy
import okhttp3.OkHttpClient
import rxhttp.RxHttp
import rxhttp.wrapper.converter.MoshiConverter
import javax.inject.Inject

class NetworkInitializer @Inject constructor(
  private val okHttpClient: Lazy<OkHttpClient>,
  private val moshi: Lazy<Moshi>
) : AppInitializer() {

  override fun run() {
    RxHttp.setDebug(BuildConfig.DEBUG)
    RxHttp.setConverter(MoshiConverter.create(moshi.get()))
    RxHttp.init(okHttpClient.get())
  }

  override fun isRunOnMainThread(): Boolean {
    return false
  }
  
}