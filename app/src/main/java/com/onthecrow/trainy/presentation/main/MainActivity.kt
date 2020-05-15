package com.onthecrow.trainy.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseActivity
import com.onthecrow.trainy.extensions.makeStatusBarTransparent
import com.onthecrow.trainy.presentation.auth.AuthFragmentDirections
import com.onthecrow.trainy.utils.VKUtils

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App)
        makeStatusBarTransparent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.isLoggedIn.observe(
            this,
            Observer {
                it?.getContentIfNotHandled()?.let {
                    findNavController(R.id.mainNavHostFragment).navigate(
                        AuthFragmentDirections.actionAuthFragmentToMainFragment()
                    )
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        VKUtils.obtainOnActivityResult(requestCode, resultCode, data, {
            it?.let { showSnackbar(it.localizedMessage ?: getString(R.string.auth_error)) }
                ?: showSnackbar(getString(R.string.auth_error))
        }) {
            findNavController(R.id.mainNavHostFragment).navigate(R.id.mainFragment)
        }
    }
}
