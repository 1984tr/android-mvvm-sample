package com.tr1984.mvvmsample.base

import android.content.Intent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel {

    var putPageSubject = PublishSubject.create<Triple<Class<*>, Int, Intent?>>()
    var popPageSubject = PublishSubject.create<Pair<Int?, Intent?>>()

    var popupSubject = PublishSubject.create<PopupBundle>()
    var errorSubject = PublishSubject.create<ErrorBundle>()
    var toastSubject = PublishSubject.create<String>()

    var compositeDiposable = CompositeDisposable()

    class PopupBundle(var title: String?, var message: String?,
                      var positiveLabel: String? = null, var positiveCallback: (() -> Unit)?,
                      var negativeLabel: String? = null, var negativeCallback: (() -> Unit)?)

    class ErrorBundle(var throwable: Throwable, var callback: (() -> Unit)? = null)
}