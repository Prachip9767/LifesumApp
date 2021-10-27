package com.example.lifesum.UI.create_acc_frags

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.UserEntity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_sign_up_with_account.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SignUpWithAccountFragment : Fragment(R.layout.fragment_sign_up_with_account) {
    private lateinit var googleSignInClient: GoogleSignInClient
    private var requestCodeGSignIn = 7;
    private lateinit var gAuth: FirebaseAuth
    private lateinit var fsRoot: FirebaseFirestore

//    private lateinit var roomDB: MainRoomDB
//    private lateinit var dao: DAO
//    private lateinit var repo: Repo
//    private lateinit var viewModel: LifeSumViewModel
//    private lateinit var viewModelFactory: LifeSumViewModelFactory

    private var goalType: Int = 0
    private lateinit var gender: String
    private var bDate: Int = 0
    private lateinit var bMonth: String
    private var bYear: Int = 0
    private var height: Int = 0
    private var curr_weight: Int = 0
    private var goal_weight: Int = 0
    private var curr_date = ""


//    override fun onStart() {
//        super.onStart()
//        val currentUser = gAuth.currentUser
//        if (currentUser != null)
//            startActivity(Intent(context, MainActivity::class.java))
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMV()
        getDataFromPreviousFragments()
        processRequestToGoogle()

        btn_signup_with_google.setOnClickListener {
            executeSignUpProcess()
        }

    }

    private fun initMV() {
        //roomDB = MainRoomDB.getMainRoomDb(this.requireActivity())
        // dao = roomDB.getDao()
        //repo = Repo(dao)
        //viewModelFactory = LifeSumViewModelFactory(repo)
        // viewModel = ViewModelProviders.of(this.requireActivity(), viewModelFactory)
        //     .get(LifeSumViewModel::class.java)
        gAuth = FirebaseAuth.getInstance()
        fsRoot = FirebaseFirestore.getInstance()
        curr_date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
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
        startActivityForResult(signInIntent, requestCodeGSignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestCodeGSignIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                //Log.d("rkpsx7", "$e")
                toast("Error in getting info :$e")
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

                    CoroutineScope(Dispatchers.IO).launch {
                        addUserDetailsToServer(user)
                    }

                    val dashboardData = DashboardEntity(curr_date, 0, 2044, 0, 0, 0, 0, 0.0)

                    CoroutineScope(Dispatchers.IO).launch {
                        addDashboardDataToServer(dashboardData)
                    }
                    startActivity(
                        Intent(
                            context, com.example.lifesum.UI.activites.MainActivity::class.java
                        )
                    )
                    activity?.finish()
                } else {
                    toast("Sign in Failed")
                }
            }
    }

    fun addUserDetailsToServer(user: UserEntity) {
        val userUID = gAuth.currentUser?.uid
        fsRoot.collection("Users").document(userUID!!).set(user)
    }

    fun addDashboardDataToServer(dsbData: DashboardEntity) {
        val userUID = gAuth.currentUser?.uid
        fsRoot.collection("Users").document(userUID!!)
            .collection("Dashboard").document(curr_date).set(dsbData)
    }

    private fun toast(str: String) {
        Toast.makeText(this.requireActivity(), str, Toast.LENGTH_SHORT).show()
    }
}