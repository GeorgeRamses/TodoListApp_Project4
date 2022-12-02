package com.udacity.project4.authentication

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseUserLiveData : LiveData<FirebaseUser?>() {
    private val firbaseAuth = FirebaseAuth.getInstance()

    private val authStateListener = FirebaseAuth.AuthStateListener {
        value = it.currentUser
    }

    override fun onActive() {
        firbaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onInactive() {
        firbaseAuth.removeAuthStateListener(authStateListener)
    }
}