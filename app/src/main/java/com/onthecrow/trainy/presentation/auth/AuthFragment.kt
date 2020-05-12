package com.onthecrow.trainy.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentAuthBinding
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.fragment_auth.*
import java.util.concurrent.TimeUnit

class AuthFragment : BaseBindingFragment<AuthViewModel, FragmentAuthBinding>() {

    companion object {
        const val REQUEST_CODE_FB = 111
    }

    override val layoutRes = R.layout.fragment_auth
    override val viewModel: AuthViewModel by viewModels { viewModelFactory }

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val callbackManager by lazy { CallbackManager.Factory.create() }
    private var storedVerificationId = ""
    private var storedResendToken: PhoneAuthProvider.ForceResendingToken? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.onPhoneAuthClick.observe(viewLifecycleOwner, Observer { event ->
            event?.hasBeenHandled?.let { if (!it) onPhoneAuthClicked() }
        })
        viewModel.onFacebookAuthClick.observe(viewLifecycleOwner, Observer { event ->
            event?.hasBeenHandled?.let { if (!it) authWithFacebook() }
        })
        viewModel.onGoogleAuthClick.observe(viewLifecycleOwner, Observer { event ->
            event?.hasBeenHandled?.let { if (!it) authWithGoogle() }
        })
        viewModel.onVKAuthClick.observe(viewLifecycleOwner, Observer { event ->
            event?.hasBeenHandled?.let { if (!it) authWithVK() }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onPhoneAuthClicked() = if (storedVerificationId.isBlank()) {
        authWithPhone()
    } else {
        signInWithPhoneAuthCredential(
            PhoneAuthProvider.getCredential(
                storedVerificationId,
                viewModel.code.value.toString()
            )
        )
    }

    private fun authWithPhone() {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                showError(e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                storedResendToken = token
                authEmailTextInputLayout.visibility = View.INVISIBLE
                authCodeTextInputLayout.visibility = View.VISIBLE
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            viewModel.phoneNumber.value.toString(), // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            requireActivity(), // Activity (for callback binding)
            callbacks
        ) // OnVerificationStateChangedCallbacks
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    authenticated()
                } else {
                    task.exception?.let { showError(it) }
                        ?: showSnackbar(getString(R.string.auth_error))
                }
            }
    }

    private fun authWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        startActivityForResult(
            GoogleSignIn.getClient(requireActivity(), gso).signInIntent,
            REQUEST_CODE_FB
        )
    }

    private fun authWithFacebook() {
        LoginManager.getInstance().run {
            registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    showSnackbar(getString(R.string.auth_cancelled))
                }

                override fun onError(error: FacebookException) {
                    showError(error)
                }
            })

            logInWithReadPermissions(this@AuthFragment, listOf("email", "public_profile"))
        }
    }

    private fun authWithVK() = checkPendingOrAuth {
        VK.login(
            context as Activity,
            arrayListOf(VKScope.WALL, VKScope.PHOTOS, VKScope.EMAIL, VKScope.FRIENDS)
        )
        FirebaseApp.initializeApp(requireActivity())
    }

    private fun authenticated() =
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToMainFragment())

    private inline fun checkPendingOrAuth(callback: () -> Unit) =
        auth.pendingAuthResult?.let { task ->
            task.addOnSuccessListener { authenticated() }
                .addOnFailureListener { showError(it) }
        } ?: callback.invoke()

    private fun handleFacebookAccessToken(token: AccessToken) =
        auth.signInWithCredential(FacebookAuthProvider.getCredential(token.token))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    authenticated()
                } else {
                    task.exception?.let { showError(it) }
                        ?: showSnackbar(getString(R.string.auth_error))
                }
            }

    private fun handleGoogleAccount(acct: GoogleSignInAccount) =
        auth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                acct.idToken,
                null
            )
        ).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                authenticated()
            } else {
                task.exception?.let { showError(it) }
                    ?: showSnackbar(getString(R.string.auth_error))
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FB) {
            try {
                GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
                    ?.let { handleGoogleAccount(it) }
            } catch (e: ApiException) {
                showError(e)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}