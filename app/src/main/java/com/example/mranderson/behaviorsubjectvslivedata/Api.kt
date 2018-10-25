package com.example.mranderson.behaviorsubjectvslivedata

import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import io.reactivex.subjects.BehaviorSubject

class Api {

    /**
     * LiveData
     */

    val state = MutableLiveData<Boolean>()

    init {
        state.value = false
    }

    fun doACall() {
        Handler().postDelayed({
            state.value = true
        }, 5000)
    }

    /**
     * BehaviorSubject
     */

    var apiStatusBehaviorSubject = BehaviorSubject.createDefault<Boolean>(false)

    fun doACallToo(){
        Handler().postDelayed({
            apiStatusBehaviorSubject.onNext(true)
        }, 5000)
    }
}