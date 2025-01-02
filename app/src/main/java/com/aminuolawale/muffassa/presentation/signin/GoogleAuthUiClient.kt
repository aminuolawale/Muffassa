package com.aminuolawale.muffassa.presentation.signin

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.aminuolawale.muffassa.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(private val context: Context) {
    private val auth = Firebase.auth
    private val oneTapClient = Identity.getSignInClient(context)


    suspend fun getSignInIntentSender(): IntentSender? {
        val beginSignInResult =
            try {
                oneTapClient.beginSignIn(buildBeginSignInRequest()).await()
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e else null
            }
        return beginSignInResult?.pendingIntent?.intentSender
    }

    private fun buildBeginSignInRequest() =
        BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.Builder().setSupported(true).setServerClientId(
                context.getString(R.string.web_client_id)
            )
                .setFilterByAuthorizedAccounts(false).build()
        ).setAutoSelectEnabled(true).build()

    suspend fun signInWithIntent(intent: Intent): SignInResult? {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        try {
            val firebaseUser: FirebaseUser? =
                auth.signInWithCredential(googleCredentials).await().user
            return SignInResult(userData = firebaseUserToUserData(firebaseUser), errorString = null)

        } catch (e: Exception) {
            return if (e is CancellationException) throw e else null
        }
    }

    fun getSignedInUser() = firebaseUserToUserData(auth.currentUser)

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
        }
    }

    private fun firebaseUserToUserData(firebaseUser: FirebaseUser?) = firebaseUser?.let {
        UserData(
            userId = it.uid,
            username = it.displayName,
            profilePictureUrl = it.photoUrl.toString()
        )
    }
}