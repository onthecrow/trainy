package com.onthecrow.trainy.utils

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback

object VKUtils {

    fun obtainOnActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        onError: (Throwable?) -> Unit,
        onSuccess: () -> Unit
    ) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword("" + token.email, "" + token.email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess.invoke()
                        } else {
                            onError.invoke(task.exception)
                        }
                    }
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        VK.onActivityResult(requestCode, resultCode, data, callback)
    }
}