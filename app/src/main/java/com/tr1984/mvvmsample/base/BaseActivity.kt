package com.tr1984.mvvmsample.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.tr1984.mvvmsample.tools.disposeBag
import com.tr1984.mvvmsample.tools.uiSubscribeWithError

open class BaseActivity<T : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: T
    protected lateinit var binding : VB

    override fun onDestroy() {
        viewModel.compositeDiposable.clear()
        super.onDestroy()
    }

    open fun subscribeSubjects() {
        viewModel.run {
            popupSubject
                .uiSubscribeWithError {

                }.disposeBag(compositeDiposable)

            errorSubject
                .uiSubscribeWithError {

                }.disposeBag(compositeDiposable)

            toastSubject
                .uiSubscribeWithError {

                }.disposeBag(compositeDiposable)
        }
    }
}