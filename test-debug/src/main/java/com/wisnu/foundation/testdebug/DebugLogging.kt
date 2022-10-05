package com.wisnu.foundation.testdebug

import android.util.Log
import com.wisnu.foundation.coreloggr.Logging

class DebugLogging : Logging {

    override fun log(priority: Int, tag: String, message: String, throwable: Throwable?) {
        when (priority) {
            Log.DEBUG -> Log.d(tag, message)
            Log.VERBOSE -> Log.v(tag, message)
            Log.ERROR -> Log.e(tag, message, throwable)
            Log.WARN -> Log.w(tag, message, throwable)
            Log.INFO -> Log.i(tag, message, throwable)
            Log.ASSERT -> Log.wtf(tag, message, throwable)
        }
    }

}
