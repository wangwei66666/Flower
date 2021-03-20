package com.ww.flower.ui.activities.ui.widget

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author ww
 * @date 2021/3/20.
 * description：
 */
class LifeCycleChronometer(context: Context?, attrs: AttributeSet?) : Chronometer(context, attrs), LifecycleObserver {

    companion object {
        var elapsedTime: Long = 0L;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resumeMeter() {
        base = SystemClock.elapsedRealtime() - elapsedTime
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun pauseMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }
}