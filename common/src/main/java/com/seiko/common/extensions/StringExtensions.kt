package com.seiko.common.extensions

import java.net.URLEncoder

fun String.toUrlEncode(): String = URLEncoder.encode(this, "UTF-8")