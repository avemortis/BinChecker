package com.vtorushin.data.utils

interface DataMapper<T> {
    fun mapToDomain(): T
}