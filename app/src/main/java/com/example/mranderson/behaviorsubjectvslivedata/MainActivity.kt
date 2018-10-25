package com.example.mranderson.behaviorsubjectvslivedata

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    private  val list = CompositeDisposable()

    private lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        api = Api()

        LiveData.setOnClickListener {
            api.doACall()
        }

        BehaviorSubject.setOnClickListener {
            api.doACallToo()
        }

        Clear.setOnClickListener {
            removeFragment()
        }

        api.state.observe(this, Observer { state ->
            if (state != null && state) {
                showFragment()
            }
        })

        disposable = api.apiStatusBehaviorSubject.subscribe { state ->
            if (state) {
                showFragment()
            }
        }
        list.add(disposable)
    }

    private fun showFragment() {
        val fragment = MainFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.Fragment, fragment, "TAG")
                .commit()
    }

    private fun removeFragment() {
        val fragment = supportFragmentManager.findFragmentByTag("TAG")
        supportFragmentManager
                .beginTransaction()
                .remove(fragment!!)
                .commit()
    }

    override fun onPause() {
        super.onPause()
        list.clear()
    }
}
