package com.example.lifesum.create_account_fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.MainActivity
import com.example.lifesum.R
import com.example.lifesum.models.UserEntity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_sign_up_with_account.*

class SignUpWithAccountFragment : Fragment(R.layout.fragment_sign_up_with_account) {
    private lateinit var googleSignInClient: GoogleSignInClient
    private var RC_SIGN_IN = 7;
    private lateinit var gAuth: FirebaseAuth

    private lateinit var dbroot: FirebaseFirestore

    private var goalType: Int = 0
    private lateinit var gender: String
    private var bDate: Int = 0
    private lateinit var bMonth: String
    private var bYear: Int = 0
    private var height: Int = 0
    private var curr_weight: Int = 0
    private var goal_weight: Int = 0


    override fun onStart() {
        super.onStart()
        val currentUser = gAuth.currentUser
        if (currentUser != null)
            startActivity(Intent(context, MainActivity::class.java))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromPreviousFragments()
        gAuth = FirebaseAuth.getInstance()
        dbroot = FirebaseFirestore.getInstance()

        processRequestToGoogle()

        btn_signup_with_google.setOnClickListener {
            executeSignUpProcess()
        }
    }

    private fun getDataFromPreviousFragments() {
        arguments?.run {
            goalType = getInt("goalType")
            gender = getString("gender").toString()
            bDate = getInt("b_date")
            bMonth = getString("b_month").toString()
            bYear = getInt("b_year")
            height = getInt("height")
            curr_weight = getInt("curr_weight")
            goal_weight = getInt("goal_weight")
        }
    }

    private fun processRequestToGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // ignore this error and RUN the app
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso)
    }

    private fun executeSignUpProcess() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.d("rkpsx7", e.toString())
                Toast.makeText(context, "Error in getting info :$e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        gAuth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    val x = gAuth.currentUser
                    val name = x?.displayName.toString()
                    val email = x?.email.toString()
                    val user = UserEntity(
                        name, email, goalType, gender, bDate, bMonth, bYear,
                        height, curr_weight, goal_weight
                    )
                    val docPath = FirebaseAuth.getInstance().currentUser!!.uid
                    dbroot.collection("Users").document(docPath).set(user)
                    //dbroot.collection("Users").document(docPath).collection("EnergyInfo").document("row1").set(user)

                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    toast("Sign in Failed")
                }
            }
    }

    private fun toast(str: String) {
        Toast.makeText(this.requireActivity(), str, Toast.LENGTH_SHORT).show()
    }
}