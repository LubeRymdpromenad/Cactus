package com.example.cactus.common

interface Mapper<T, U> {
    fun map(model: T): U
}