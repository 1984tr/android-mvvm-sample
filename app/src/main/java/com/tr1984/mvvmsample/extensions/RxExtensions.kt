package com.tr1984.mvvmsample.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.uiSubscribe(onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer(onNext), Consumer(onError))
}

fun <T> Observable<T>.uiSubscribeWithError(onNext: (T) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer(onNext), Consumer { it.printStackTrace() })
}

fun <T> Single<T>.uiSubscribe(onNext: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer(onNext), Consumer(onError))
}

fun <T> Single<T>.uiSubscribeWithError(onNext: (T) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer(onNext), Consumer { it.printStackTrace() })
}

fun Completable.uiSubscribe(onNext: () -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Action(onNext), Consumer(onError))
}

fun Completable.uiSubscribeWithError(onNext: () -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Action(onNext), Consumer { it.printStackTrace() })
}

fun Disposable.disposeBag(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}