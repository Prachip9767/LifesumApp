package com.example.lifesum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lifesum.models.UserEntity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dbroot: FirebaseFirestore
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        dbroot = FirebaseFirestore.getInstance()
        getUserDetailsFromGoogle()
        getUserDetailsFromServer()
    }

    private fun getUserDetailsFromServer() {
        val uid = auth.currentUser!!.uid
        val documentReference = dbroot.collection("Users").document(uid)
        documentReference.get().addOnSuccessListener {
            if (it.exists()){
                val name = it.getString("name_of_user")
                val email = it.getString("email")
                val gender = it.getString("gender")
                val goalType = it.getString("goalType")

            }
        }

    }

    private fun getUserDetailsFromGoogle() {
        var account = GoogleSignIn.getLastSignedInAccount(this)
        val email = account.email
        val name = account.displayName
        val photo = account.photoUrl

        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .database

        gName.text = name
        gEmail.text = email
        Glide.with(this).load(photo).into(gPic)
    }
}
