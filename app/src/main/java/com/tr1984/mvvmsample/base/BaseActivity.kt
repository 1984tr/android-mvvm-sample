package com.tr1984.mvvmsample.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tr1984.mvvmsample.BR
import com.tr1984.mvvmsample.extensions.alert
import com.tr1984.mvvmsample.extensions.disposeBag
import com.tr1984.mvvmsample.extensions.toast
import com.tr1984.mvvmsample.extensions.uiSubscribeWithError
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    abstract var viewModel: T
    abstract var layoutId: Int

    protected lateinit var binding: VB
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        subscribeSubjects()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    open fun subscribeSubjects() {
        viewModel.run {

            navigator
                .uiSubscribeWithError {
                    when (it) {
                        is BaseViewModel.Navigator.Start -> {
                            val intent = (it.intent ?: Intent()).apply {
                                setClass(this@BaseActivity, it.clazz)
                            }
                            if ((it.requestCode ?: 0) > 0) {
                                startActivityForResult(intent, it.requestCode ?: 0)
                            } else {
                                startActivity(intent)
                            }
                        }
                        is BaseViewModel.Navigator.Finish -> {
                            it.resultCode?.let { rc ->
                                setResult(rc, it.intent)
                                finish()
                            } ?: finish()
                        }
                    }
                }.disposeBag(compositeDisposable)

            notifier
                .uiSubscribeWithError {
                    when (it) {
                        is BaseViewModel.Notifier.Toast -> {
                            this@BaseActivity.toast(it.message)
                        }
                        is BaseViewModel.Notifier.Alert -> {
                            this@BaseActivity.alert(
                                it.title,
                                it.message ?: "",
                                it.positive?.label,
                                it.positive?.onClick,
                                it.negative?.label,
                                it.negative?.onClick
                            )
                        }
                        is BaseViewModel.Notifier.Error -> {
                            this@BaseActivity.alert(
                                message = it.throwable?.localizedMessage,
                                positiveCallback = it.onClick
                            )
                        }
                    }
                }.disposeBag(compositeDisposable)
        }
    }
}