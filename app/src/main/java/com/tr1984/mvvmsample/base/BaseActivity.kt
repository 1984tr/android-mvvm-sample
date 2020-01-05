package com.tr1984.mvvmsample.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.util.disposeBag
import com.tr1984.mvvmsample.util.uiSubscribeWithError

abstract class BaseActivity<T : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    abstract var viewModel: T
    abstract var layoutId: Int

    protected lateinit var binding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        subscribeSubjects()
    }

    override fun onDestroy() {
        viewModel.compositeDiposable.clear()
        super.onDestroy()
    }

    open fun subscribeSubjects() {
        viewModel.run {
            startPageSubject
                .uiSubscribeWithError {
                    val intent = (it.intent ?: Intent()).apply {
                        setClass(this@BaseActivity, it.clazz)
                    }
                    if ((it.requestCode ?: 0) > 0) {
                        startActivityForResult(intent, it.requestCode ?: 0)
                    } else {
                        startActivity(intent)
                    }
                }.disposeBag(compositeDiposable)

            finishPageSubject
                .uiSubscribeWithError {
                    it.resultCode?.let { rc ->
                        setResult(rc, it.intent)
                        finish()
                    } ?: finish()
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