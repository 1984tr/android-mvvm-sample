package com.tr1984.mvvmsample.base

import android.content.Intent
import androidx.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel(open val compositeDisposable: CompositeDisposable) {

    val navigator by lazy { PublishSubject.create<Navigator>() }
    val notifier by lazy { PublishSubject.create<Notifier>() }
    val isLoading by lazy { ObservableBoolean(false) }

    sealed class Navigator {

        class Start(var clazz: Class<*>, var requestCode: Int? = null, var intent: Intent? = null) :
            Navigator()

        class Finish(var resultCode: Int? = null, var intent: Intent? = null) : Navigator()
    }

    sealed class Notifier {

        class Toast(val message: String) : Notifier()
        class Alert(
            val title: String?,
            val message: String,
            val positive: Button? = null,
            val negative: Button? = null
        ) : Notifier() {
            class Button(val label: String? = "확인", val onClick: (() -> Unit)? = null)
        }

        class Error(val throwable: Throwable?, val onClick: (() -> Unit)? = null) : Notifier()
    }
}