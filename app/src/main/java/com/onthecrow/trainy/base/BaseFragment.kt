package com.onthecrow.trainy.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.onthecrow.trainy.di.viewmodel.ViewModelFactory
import com.onthecrow.trainy.presentation.progress.ProgressDialog
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject


abstract class BaseFragment<T : BaseViewModel> : DaggerFragment() {

    private val progressDialog by lazy { ProgressDialog.newInstance() }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner, Observer(::showError))
        viewModel.errorText.observe(viewLifecycleOwner, Observer(::showSnackbar))
    }

    private fun showProgress(isShow: Boolean) {
        if (isShow) {
            progressDialog.show(activity?.supportFragmentManager ?: return)
        } else {
            progressDialog.dismiss()
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun showError(error: Throwable) {
        error.localizedMessage?.let { message ->
            showSnackbar(message)
        }
        Timber.e(error)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun showSnackbar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        val imm: InputMethodManager? =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
        super.onDestroyView()
    }
}