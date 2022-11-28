package com.mywebsite.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentToAnotherScreen = Intent( this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

//        database = Firebase.database.reference
//
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build())
//
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//        signInLauncher.launch(signInIntent)

    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {

            val authUser = FirebaseAuth.getInstance().currentUser
            authUser?.let {
                val email = it.email.toString()
                val uid = it.uid
                val firebaseUser = User(email, uid)
                database.child("users").child(uid).setValue(firebaseUser)

                val intentToAnotherScreen = Intent( this, MoviesActivity::class.java)
                startActivity(intentToAnotherScreen)
            }

        } else {
            Toast.makeText(this@MainActivity, "Something wrong with App (registration)!", Toast.LENGTH_SHORT).show()
        }
    }

}