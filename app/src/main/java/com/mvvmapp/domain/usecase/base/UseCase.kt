package com.mvvmapp.domain.usecase.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {

    protected var lastDisposable: Disposable? = null
    protected var compositeDisposable =  CompositeDisposable()

    fun disposeLast() {
        lastDisposable?.let {
            if(!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}