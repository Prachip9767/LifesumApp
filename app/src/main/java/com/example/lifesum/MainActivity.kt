package com.example.lifesum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.UI.DiaryFragment

class MainActivity : AppCompatActivity(), onBackPressForFragment {

    private var goalType: Int = 0
    private var gender: String = ""
    private var name: String = ""
    private var email: String = ""
    private var b_date: Int = 0
    private var b_month: String = ""
    private var b_year: Int = 0
    private var height: Int = 0
    private var curr_weight: Int = 0
    private var goal_weight: Int = 0

    //    private lateinit var dbroot: FirebaseFirestore
//    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val diaryFragment = DiaryFragment(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.mainActivityFragmentContainer, diaryFragment)
            .commit()

//        Water.setOnClickListener {
//            Water.playAnimation()
//        }
//        Water2.setOnClickListener {
//            Water2.playAnimation()
//        }
//        Water3.setOnClickListener {
//            Water3.playAnimation()
//        }
//        Water4.setOnClickListener {
//            Water4.playAnimation()
//        }
//        setContentView(R.layout.fragment_d_iary)
//        auth = FirebaseAuth.getInstance()
//        dbroot = FirebaseFirestore.getInstance()
//        getUserDetailsFromServer()


    }

    override fun onCancelPressForFragment() {
        onBackPressed()
    }


//    private fun getUserDetailsFromServer() {
//        val uid = auth.currentUser?.uid
//        Log.d("rkpsx7", uid.toString())
//        val documentReference = dbroot.collection("Users").document(uid.toString())
//        documentReference.get().addOnSuccessListener {
//            if (it.exists()) {
//                name = it.getString("name_of_user").toString()
//                Log.d("rkpsx7", name.toString())
//                email = it.getString("email").toString()
//                goalType = it.get("goalType").toString().toInt()
//                gender = it.getString("gender").toString()
//                b_date = it.get("b_date").toString().toInt()
//                Log.d("rkpsx7", b_date.toString())
//                b_month = it.getString("b_month").toString()
//                b_year = it.get("b_year").toString().toInt()
//                Log.d("rkpsx7", b_year.toString())
//                height = it.get("height").toString().toInt()
//                curr_weight = it.get("curr_weight").toString().toInt()
//                goal_weight = it.get("goal_weight").toString().toInt()
//            }
//        }
}

//    private fun getUserDetailsFromGoogle() {
//        var account = GoogleSignIn.getLastSignedInAccount(this)
//        val email = account.email
//        val name = account.displayName
//        val photo = account.photoUrl
//
//        FirebaseDatabase.getInstance().getReference("Users")
//            .child(FirebaseAuth.getInstance().currentUser!!.uid)
//            .database
//
//        gName.text = name
//        gEmail.text = email
//        Glide.with(this).load(photo).into(gPic)
//    }
