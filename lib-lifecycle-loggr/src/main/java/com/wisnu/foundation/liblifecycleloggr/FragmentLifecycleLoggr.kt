package com.wisnu.foundation.liblifecycleloggr

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.wisnu.foundation.coreloggr.Loggr

internal class FragmentLifecycleLoggr : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(fragmentManager: FragmentManager, fragment: Fragment, context: Context) {
        Loggr.record { "$TAG - ${fragment.className()} onAttached()" }
    }

    override fun onFragmentCreated(fragmentManager: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
        Loggr.record { "$TAG - ${fragment.className()} onCreated(savedInstanceState: $savedInstanceState)" }
    }

    override fun onFragmentViewCreated(fragmentManager: FragmentManager, fragment: Fragment, view: View, savedInstanceState: Bundle?) {
        Loggr.record { "$TAG - ${fragment.className()} onViewCreated(savedInstanceState: $savedInstanceState, sizeKb: ${savedInstanceState.dataSize().toKb()}" }
    }

    override fun onFragmentStarted(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onStarted()" }
    }

    override fun onFragmentResumed(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onResumed()" }
    }

    override fun onFragmentPaused(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onPaused()" }
    }

    override fun onFragmentStopped(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onStopped()" }
    }

    override fun onFragmentViewDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onViewDestroyed()" }
    }

    override fun onFragmentDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onDestroyed()" }
    }

    override fun onFragmentDetached(fragmentManager: FragmentManager, fragment: Fragment) {
        Loggr.record { "$TAG - ${fragment.className()} onDetached()" }
    }

    override fun onFragmentSaveInstanceState(fragmentManager: FragmentManager, fragment: Fragment, outState: Bundle) {
        Loggr.record { "$TAG - ${fragment.className()} onSaveInstanceState(outState: $outState, sizeKb: ${outState.dataSize().toKb()})" }
    }

    private fun Fragment?.className() = this?.javaClass?.canonicalName.orEmpty()

    companion object {
        private const val TAG = "FragmentLifecycleLoggr"
    }
}
