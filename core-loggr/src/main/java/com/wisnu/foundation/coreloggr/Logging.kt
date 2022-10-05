package com.wisnu.foundation.coreloggr

interface Logging {
    fun log(priority: Int, tag: String, message: String, throwable: Throwable?)
}
