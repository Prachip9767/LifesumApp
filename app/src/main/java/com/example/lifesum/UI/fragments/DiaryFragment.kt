package com.example.lifesum.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.R
import com.example.lifesum.UI.activites.onBackPressForFragment
import com.example.lifesum.models.RecipeModel
import com.example.lifesum.repositary.Repo
import com.example.lifesum.viewmodels.LifeSumViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dumy.*


class DiaryFragment(val onBackPress: onBackPressForFragment) : Fragment(R.layout.dumy) {

    private lateinit var roomDB: MainRoomDB
    private lateinit var dao: DAO
    private lateinit var repo: Repo
    private lateinit var viewModel: LifeSumViewModel
    private var dataList = ArrayList<RecipeModel>()

    private var uid: String? = null
    lateinit var userRef: DocumentReference
    lateinit var dashboardRef: DocumentReference
    private lateinit var dbroot: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var foodItemRef: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initMV()
//        Water.speed = 5F
//        Water2.speed = 5F
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
        getDailyMealDataFromServer()
        setToTextView()

//        select_date.setOnClickListener {
//            val calenderFragment = CalenderFragment(onBackPress)
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.add(R.id.mainActivityFragmentContainer, calenderFragment)
//                ?.addToBackStack("")
//                ?.commit()
//        }

    }

    private fun setToTextView() {

    }

    private fun getDailyMealDataFromServer() {

        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map { it.getValue(RecipeModel::class.java)!! }
                dataList.clear()
                dataList = items as ArrayList<RecipeModel>

                Log.d("rkpsx7", dataList.size.toString())
                val data = dataList[0]
                tv_dumy.text = data.ingredients

                //val dailyMealData = DailyMealData("01-10-2021", "breakfast", dataList)

//                CoroutineScope(Dispatchers.IO).launch {
//                    dao.insertToMealData(dailyMealData)
//                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        foodItemRef.addValueEventListener(dataListener)
    }

    private fun initMV() {
        roomDB = MainRoomDB.getMainRoomDb(this.requireActivity())
        dao = roomDB.getDao()
        repo = Repo(dao)
        viewModel = LifeSumViewModel(repo)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbroot = FirebaseFirestore.getInstance()
        uid = auth.currentUser?.uid
        foodItemRef = db.getReference("Recipes")
    }

}
