package com.seiko.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
  val page: Int = 0,
  val name: String,
  val url: String,
) {
  val imageUrl: String by lazy {
    val index = url.split("/".toRegex()).dropLast(1).last()
    "https://pokeres.bastionbot.org/images/pokemon/$index.png"
  }
}