package com.greenbot.cinema

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform