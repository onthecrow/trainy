package com.onthecrow.trainy.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.onthecrow.trainy.di.viewmodel.ViewModelFactory
import com.onthecrow.trainy.presentation.progress.ProgressDialog
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel> : DaggerAppCompatActivity() {

    private val progressDialog by lazy { ProgressDialog.newInstance() }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.error.observe(this, Observer(::showError))
        viewModel.errorText.observe(this, Observer(::showSnackbar))
    }

    private fun showProgress(isShow: Boolean) {
        if (isShow) {
            progressDialog.show(supportFragmentManager)
        } else if (progressDialog.isResumed) {
            progressDialog.dismiss()
        }
    }

    private fun showError(error: Throwable) {
        error.localizedMessage?.let {
            showSnackbar(it)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun showSnackbar(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG).show()
    }
}