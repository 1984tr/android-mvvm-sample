package com.tr1984.mvvmsample.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribeWithError

open class BaseActivity<T : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: T
    protected lateinit var binding : VB

    override fun onDestroy() {
        viewModel.compositeDiposable.clear()
        super.onDestroy()
    }

    open fun subscribeSubjects() {
        viewModel.run {
            putPageSubject
                .uiSubscribeWithError {
                    val clazz = it.first
                    val requestCode = it.second
                    val intent = (it.third ?: Intent()).apply {
                        setClass(this@BaseActivity, clazz)
                    }
                    if (requestCode > 0) {
                        startActivityForResult(intent, requestCode)
                    } else {
                        startActivity(intent)
                    }
                }.disposeBag(compositeDiposable)

            popPageSubject
                .uiSubscribeWithError {
                    val resultCode = it.first
                    val intent = it.second
                    if (resultCode == null) {
                        finish()
                    } else {
                        setResult(resultCode, intent)
                        finish()
                    }
                }.disposeBag(compositeDiposable)

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