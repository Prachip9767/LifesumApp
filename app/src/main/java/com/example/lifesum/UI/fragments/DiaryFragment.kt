package com.example.lifesum.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.R
import com.example.lifesum.UI.activites.onBackPressForFragment
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.FoodItem
import com.example.lifesum.repositary.Repo
import com.example.lifesum.viewmodels.LifeSumViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_diary.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DiaryFragment(val onBackPress: onBackPressForFragment) : Fragment(R.layout.fragment_diary) {

    private lateinit var roomDB: MainRoomDB
    private lateinit var dao: DAO
    private lateinit var repo: Repo
    private lateinit var viewModel: LifeSumViewModel
    private var dataList = ArrayList<FoodItem>()

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
        viewModel.getDashboardDataFromDb().observe(this.requireActivity(), Observer {

        })
        getDailyMealDataFromServer()

        val materialDateBuilder: MaterialDatePicker.Builder<Long> =
            MaterialDatePicker.Builder.datePicker()

        materialDateBuilder.setTitleText("Select a date")
        val materialDatePicker = materialDateBuilder.build()


        select_date.setOnClickListener {

            materialDatePicker.show(requireFragmentManager(), "MATERIAL_DATE_PICKER");
        }
        materialDatePicker.addOnPositiveButtonClickListener { selection -> // Get the offset from our timezone and UTC.
            val timeZoneUTC = TimeZone.getDefault()

            val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1

            val simpleFormat = SimpleDateFormat("EEEE, dd MMM", Locale.US)
            val date = Date(selection + offsetFromUTC)
            select_date.text = simpleFormat.format(date)
        }


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
                val items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
                dataList.clear()
                dataList = items as ArrayList<FoodItem>

                Log.d("rkpsx7", dataList[0].toString())
//                val data = dataList[0]
//                tv_dumy.text = data.type

                val dailyMealData = DailyMealData("01-10-2021", "breakfast", dataList)

                CoroutineScope(Dispatchers.IO).launch {
                    dao.insertToMealData(dailyMealData)
                }
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
        foodItemRef = db.getReference("FoodItems")
    }

}
