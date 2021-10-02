package com.example.lifesum.UI.activites

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.R
import com.example.lifesum.UI.RecipeListFragment
import com.example.lifesum.UI.fragments.DiaryFragment
import com.example.lifesum.UI.fragments.PlansFragment
import com.example.lifesum.UI.fragments.ProfileFragment
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.FoodItem
import com.example.lifesum.repositary.Repo
import com.example.lifesum.viewmodels.LifeSumViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), onBackPressForFragment {


//    private lateinit var roomDB: MainRoomDB
//    private lateinit var dao: DAO
//    private lateinit var repo: Repo
//    private lateinit var viewModel: LifeSumViewModel
//
//    private var uid: String? = null
//    lateinit var userRef: DocumentReference
//    lateinit var dashboardRef: DocumentReference
//    private lateinit var dbroot: FirebaseFirestore
//    private lateinit var auth: FirebaseAuth
//    private lateinit var db: FirebaseDatabase
//    private lateinit var foodItemRef: DatabaseReference
//    private var dataList = ArrayList<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initMV()
        bottomNavigation()

        //insertToDailyMealDB()
        viewModel.getDashboardDataFromServer()//and add to room
       // viewModel.getUserDetailsFromServer()//and add to room
    }

    private fun insertToDailyMealDB() {

        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
                dataList.clear()
                dataList = items as ArrayList<FoodItem>

                val dailyMealData = DailyMealData("01-10-2021", "breakfast", dataList)

                CoroutineScope(Dispatchers.IO).launch {
                    dao.insertToMealData(dailyMealData)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                toast("Error in fetching data")
            }
        }

        foodItemRef.addValueEventListener(dataListener)
    }

    private fun initMV() {
//        roomDB = MainRoomDB.getMainRoomDb(this)
//        dao = roomDB.getDao()
//        repo = Repo(dao)
//        viewModel = LifeSumViewModel(repo)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbroot = FirebaseFirestore.getInstance()
        uid = auth.currentUser?.uid

        foodItemRef = db.getReference("FoodItems")
        userRef = dbroot.collection("Users").document(uid!!)
        dashboardRef = dbroot.collection("Users").document(uid!!)
            .collection("Dashboard").document(uid!!)
    }

    private fun bottomNavigation() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainActivityFragmentContainer, DiaryFragment(this))
            .commit()
        botton_navigation.setOnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> temp = DiaryFragment(this)
                R.id.menu_search -> temp = ProfileFragment()
                R.id.menu_orders -> temp = PlansFragment()
                R.id.menu_dunzo_cash -> temp = RecipeListFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainActivityFragmentContainer, temp)
                    .commit()
            }
            true
        }
    }


    fun lg(str: String) {
        Log.d("rkpsx7", str)
    }

    override fun onCancelPressForFragment() {
        onBackPressed()
    }
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
