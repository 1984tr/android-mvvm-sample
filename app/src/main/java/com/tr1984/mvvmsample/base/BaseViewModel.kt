package com.tr1984.mvvmsample.base

import android.content.Intent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel(val compositeDisposable: CompositeDisposable) {

    val navigator by lazy { PublishSubject.create<Navigator>() }
    val notifier by lazy { PublishSubject.create<Notifier>() }

    sealed class Navigator {

        class Start(var clazz: Class<*>, var requestCode: Int? = null, var intent: Intent? = null) :
            Navigator()

        class Finish(var resultCode: Int? = null, var intent: Intent? = null)
    }

    sealed class Notifier {

        class Loading(val show: Boolean) : Notifier()
        class Toast(val message: String) : Notifier()
        class Alert(
            val title: String?,
            val message: String,
            val positive: Pair<String, (() -> Unit)?>,
            val negative: Pair<String, (() -> Unit)?>?
        ) : Notifier()

        class Error(val throwable: Throwable?, val submit: (() -> Unit)?) : Notifier()
    }
}