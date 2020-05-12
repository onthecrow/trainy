package com.onthecrow.trainy.presentation.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.onthecrow.trainy.R

class ProgressDialog : DialogFragment() {

    companion object {
        const val TAG = "ProgressDialog"
        fun newInstance() = ProgressDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NO_FRAME, R.style.DialogTransparentBackground)
        isCancelable = false
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_progress, container, false)

    fun show(manager: FragmentManager) {
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        super.show(transaction, TAG)
    }
}