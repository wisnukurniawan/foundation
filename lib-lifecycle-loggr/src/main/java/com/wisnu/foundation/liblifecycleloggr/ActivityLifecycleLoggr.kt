package com.wisnu.foundation.liblifecycleloggr

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.wisnu.foundation.coreloggr.Loggr

class ActivityLifecycleLoggr() : Application.ActivityLifecycleCallbacks {

    private val fragmentLifecycleLogger = FragmentLifecycleLoggr()

    override fun onActivityPaused(activity: Activity) {
        Loggr.record { "$TAG - ${activity.className()} onPause()" }
    }

    override fun onActivityResumed(activity: Activity) {
        Loggr.record { "$TAG - ${activity.className()} onResume()" }
    }

    override fun onActivityStarted(activity: Activity) {
        Loggr.record { "$TAG - ${activity.className()} onStart()" }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Loggr.record { "$TAG - ${activity.className()} onSaveInstanceState(outState: $outState, sizeKb: ${outState.dataSize().toKb()})" }
    }

    override fun onActivityStopped(activity: Activity) {
        Loggr.record { "$TAG - ${activity.className()} onStop()" }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Loggr.record { "$TAG - ${activity.className()} onCreate(savedInstanceState: $savedInstanceState, sizeKb: ${savedInstanceState.dataSize().toKb()})" }

        registerFragmentLifecycleLogger(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Loggr.record { "$TAG - ${activity.className()} onDestroy()" }
    }

    private fun registerFragmentLifecycleLogger(activity: Activity?) {
        if (activity is FragmentActivity) {
            activity
                .supportFragmentManager
                .registerFragmentLifecycleCallbacks(fragmentLifecycleLogger, false)
        }
    }

    private fun Activity?.className() = this?.javaClass?.canonicalName.orEmpty()

    companion object {
        private const val TAG = "ActivityLifecycleLoggr"
    }
}
