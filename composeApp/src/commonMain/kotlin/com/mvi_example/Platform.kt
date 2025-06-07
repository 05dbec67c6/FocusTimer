package com.mvi_example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform