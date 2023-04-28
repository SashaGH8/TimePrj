package com.example.timeprj.fragments

import android.os.SystemClock
import androidx.fragment.app.Fragment
import com.example.timeprj.viewmodels.AbstractViewModel


abstract class AbstractFragment<T: AbstractViewModel?>(protected val viewModel : T? = null) : Fragment() {
    private var lastDepClick = 0L

    fun preventButtonClickSpam(f: () -> Unit) {
        if (SystemClock.elapsedRealtime() - lastDepClick > 1000) {
            lastDepClick = SystemClock.elapsedRealtime()
            f()
        }
    }

}