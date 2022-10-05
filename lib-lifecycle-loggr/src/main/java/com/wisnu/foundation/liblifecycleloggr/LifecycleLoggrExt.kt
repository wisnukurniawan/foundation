package com.wisnu.foundation.liblifecycleloggr

import android.os.Bundle
import android.os.Parcel


/**
 * Source from:
 *      https://github.com/guardian/toolargetool/blob/master/toolargetool
 *      /src/main/java/com/gu/toolargetool/TooLargeTool.kt#L162
 */
internal fun Bundle?.dataSize(): Int {
    if (this == null) return 0

    val parcel = Parcel.obtain()

    return try {
        parcel.writeBundle(this)
        parcel.dataSize()
    } catch (e: Exception) {
        0
    } finally {
        parcel.recycle()
    }
}

internal fun Int.toKb(): Float {
    return this.toFloat() / 1000f
}
