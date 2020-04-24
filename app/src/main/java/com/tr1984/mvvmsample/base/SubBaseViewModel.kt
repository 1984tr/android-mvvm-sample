package com.tr1984.mvvmsample.base

abstract class SubBaseViewModel(open val layoutId: Int, open val parent: BaseViewModel) {

    abstract fun identification() : String

    override fun equals(other: Any?): Boolean {
        return (other as? SubBaseViewModel)?.run {
            identification() == this@SubBaseViewModel.identification()
        } ?: super.equals(other)
    }
}