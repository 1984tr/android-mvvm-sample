package com.tr1984.mvvmsample.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel {

    var popupSubject = PublishSubject.create<PopupBundle>()
    var errorSubject = PublishSubject.create<ErrorBundle>()
    var toastSubject = PublishSubject.create<String>()
    var compositeDiposable = CompositeDisposable()

    class PopupBundle(var title: String?, var message: String?,
                      var positiveLabel: String? = null, var positiveCallback: (() -> Unit)?,
                      var negativeLabel: String? = null, var negativeCallback: (() -> Unit)?)

    class ErrorBundle(var throwable: Throwable, var callback: (() -> Unit)? = null)
}