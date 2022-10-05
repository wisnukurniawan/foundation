package com.wisnu.foundation.corelogger

interface Logging {
    fun log(priority: Int, tag: String, message: String, throwable: Throwable?)
}
