package com.seiko.base.mapper

interface BeanMapper<T, R> {
  fun mapTo(model: T): R
}

fun <T, R> BeanMapper<T, R>.mapToList(list: List<T>): List<R> {
  return list.mapTo(ArrayList(list.size), this::mapTo)
}