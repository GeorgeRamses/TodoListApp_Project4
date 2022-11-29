package com.udacity.project4.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.udacity.project4.R
import com.udacity.project4.databinding.ActivityAuthenticationBinding
import com.udacity.project4.locationreminders.RemindersActivity

const val SIGN_IN_REQUEST_CODE = 1001

/**
 * This class should be the starting point of the app, It asks the users to sign in / register, and redirects the
 * signed in users to the RemindersActivity.
 */
class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAuthenticationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_authentication)

        binding.btnLogin.setOnClickListener {
            // login()
        }

    }
//         TODO: Implement the create account and sign in using FirebaseUI, use sign in using email and sign in using Google

//          TODO: If the user was authenticated, send him to RemindersActivity

//          TODO: a bonus is to customize the sign in flow to look nice using :
    //https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#custom-layout


    /**  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == SIGN_IN_REQUEST_CODE) {
    val response = IdpResponse.fromResultIntent(data)
    if (resultCode == Activity.RESULT_OK) {
    val intent = Intent(this, RemindersActivity::class.java)
    startActivity(intent)

    } else {

    }
    }
    }*/

    /** fun login() {
    val provider = arrayListOf(
    AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
    )

    startActivityForResult(
    AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(provider).build(),
    SIGN_IN_REQUEST_CODE
    )
    }*/

}

