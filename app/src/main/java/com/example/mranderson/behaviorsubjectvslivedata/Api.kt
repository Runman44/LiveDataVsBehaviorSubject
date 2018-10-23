package com.example.mranderson.behaviorsubjectvslivedata

import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import io.reactivex.subjects.BehaviorSubject

class Api {

    val state = MutableLiveData<Boolean>()
    var apiStatusBehaviorSubject = BehaviorSubject.createDefault<Boolean>(false)

    init {
        state.value = false
    }

    fun doACall() {
        Handler().postDelayed({
            state.postValue(true)
        }, 5000)
    }

    fun doACallToo(){
        Handler().postDelayed({
            apiStatusBehaviorSubject.onNext(true)
        }, 5000)
    }
}