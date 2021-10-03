package com.example.lifesum.UI.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.R
import com.example.lifesum.UI.activites.MealRecordActivity
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.repositary.Repo
import com.example.lifesum.viewmodels.LifeSumViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_diary.*
import java.text.SimpleDateFormat
import java.util.*


class DiaryFragment : Fragment(R.layout.fragment_diary) {

    private var eaten: Int = 0
    private var kcal: Int = 0
    private var burned: Int = 0
    private var protein: Int = 0
    private var carbs: Int = 0
    private var fat: Int = 0

    private lateinit var roomDB: MainRoomDB
    private lateinit var dao: DAO
    private lateinit var repo: Repo
    private lateinit var viewModel: LifeSumViewModel

    private var curr_date: String = ""
    private var dsh_date: String = ""

    private var uid: String? = null
    lateinit var userRef: DocumentReference
    lateinit var dashboardRef: DocumentReference
    private lateinit var dbroot: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var foodItemRef: DatabaseReference
    private var water_glasses: Double = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initMV()
        initDate()
        setGlasses()
        inflateDataToDashboard()
        setSectionClicks()


    }

    fun updateDashBoardToDB() {
        val dsh = DashboardEntity(dsh_date, eaten, kcal, burned, carbs, protein, fat, water_glasses)
        viewModel.updateDashBoardInDB(dsh)
    }

    fun setGlasses() {
//        //glassAa.setOnClickListener {
//            //glassAa.isVisible = false
//            glassA.isVisible = true
//            glassA.playAnimation()
//            glassA.speed = 5f
//        }

        glassA.setOnClickListener {
            //glassAa.isVisible = true
            //glassA.isVisible = false
            glassA.playAnimation()
            glassA.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()

        }

//        glassCc.setOnClickListener {
//            glassCc.isVisible = false
//            glassC.isVisible = true
//            glassC.playAnimation()
//            glassC.speed = 5f
//        }

        glassC.setOnClickListener {
//            glassCc.isVisible = true
//            glassC.isVisible = false
            glassC.playAnimation()
            glassC.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
//        glassBb.setOnClickListener {
//            glassBb.isVisible = false
//            glassB.isVisible = true
//            glassB.playAnimation()
//            glassB.speed = 5f
//        }

        glassB.setOnClickListener {
//            glassBb.isVisible = true
//            glassB.isVisible = false
            glassB.playAnimation()
            glassB.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
//        glassDd.setOnClickListener {
//            glassDd.isVisible = false
//            glassD.isVisible = true
//            glassD.playAnimation()
//            glassD.speed = 5f
//        }

        glassD.setOnClickListener {
//            glassDd.isVisible = true
//            glassD.isVisible = false
            glassD.playAnimation()
            glassD.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
//        glassEe.setOnClickListener {
//            glassEe.isVisible = false
//            glassE.isVisible = true
//            glassE.playAnimation()
//            glassE.speed = 5f
//        }

        glassE.setOnClickListener {
            //glassEe.isVisible = true
            //glassE.isVisible = false
            glassE.playAnimation()
            glassE.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
//        glassFf.setOnClickListener {
//            glassFf.isVisible = false
//            glassF.isVisible = true
//            glassF.playAnimation()
//            glassF.speed = 5f
//        }

        glassF.setOnClickListener {
//            glassFf.isVisible = true
//            glassF.isVisible = false
            glassF.playAnimation()
            glassF.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
//        glassGg.setOnClickListener {
//            glassGg.isVisible = false
//            glassG.isVisible = true
//            glassG.playAnimation()
//            glassG.speed = 5f
//        }

        glassG.setOnClickListener {
//            glassGg.isVisible = true
//            glassG.isVisible = false
            glassG.playAnimation()
            glassG.speed = 5f
            water_glasses += 0.25
            tv_water_content.text = "${water_glasses.toString()} L"
            updateDashBoardToDB()
        }
    }

    private fun setSectionClicks() {
        breakfast_section.setOnClickListener {
            val intent = Intent(this.requireActivity(), MealRecordActivity::class.java)
            intent.putExtra("foodType", "Breakfast")
            intent.putExtra("curr_date", dsh_date)
            intent.putExtra("water", water_glasses)
            startActivity(intent)
        }
        lunchSection.setOnClickListener {
            val intent = Intent(this.requireActivity(), MealRecordActivity::class.java)
            intent.putExtra("foodType", "Lunch")
            intent.putExtra("curr_date", dsh_date)
            intent.putExtra("water", water_glasses)
            startActivity(intent)
        }

        dinnerSection.setOnClickListener {
            val intent = Intent(this.requireActivity(), MealRecordActivity::class.java)
            intent.putExtra("foodType", "Dinner")
            intent.putExtra("curr_date", dsh_date)
            intent.putExtra("water", water_glasses)
            startActivity(intent)
        }

        snackSection.setOnClickListener {
            val intent = Intent(this.requireActivity(), MealRecordActivity::class.java)
            intent.putExtra("foodType", "Snack")
            intent.putExtra("curr_date", dsh_date)
            intent.putExtra("water", water_glasses)
            startActivity(intent)
        }

        exerciseSection.setOnClickListener {
            val intent = Intent(this.requireActivity(), MealRecordActivity::class.java)
            intent.putExtra("foodType", "Exercise")
            intent.putExtra("curr_date", dsh_date)
            intent.putExtra("water", water_glasses)
            startActivity(intent)
        }

    }


    private fun inflateDataToDashboard() {
        Log.d("rkpsx7-x", dsh_date.toString())
        viewModel.getDashboardDataFromDb(dsh_date).observe(this.requireActivity(), Observer {
            if (it != null) {
                tv_eaten.text = it?.eaten.toString()
                tv_carbs.text = it?.carbs.toString()
                tv_burned.text = it?.burned.toString()
                tv_fat.text = it?.fat.toString()
                tv_protein.text = it?.protein.toString()
                tv_kacl_left.text = it?.kacl.toString()
                water_glasses = it?.water_glass!!
            } else {
                tv_eaten.text = "0"
                tv_carbs.text = "0"
                tv_burned.text = "0"
                tv_fat.text = "0"
                tv_protein.text = "0"
                tv_kacl_left.text = "0"

                water_glasses = 0.0
                dsh_date = dsh_date
                eaten = 0
                kcal = 0
                burned = 0
                carbs = 0
                protein = 0
                fat = 0
                water_glasses = 0.0
                updateDashBoardToDB()
            }
        })
    }

    private fun initDate() {
        dsh_date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        curr_date = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault()).format(Date())
        select_date.text = curr_date
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
            val simpleFormat1 = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            Log.d("rkpsx7", simpleFormat1.toString())
            val date = Date(selection + offsetFromUTC)
            select_date.text = simpleFormat.format(date)
            dsh_date = simpleFormat1.format(date)
            Log.d("rkpsx7", dsh_date.toString())
            inflateDataToDashboard()
        }
    }


//    private fun getDailyMealDataFromServer() {
//
//        val dataListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
//                dataList.clear()
//                dataList = items as ArrayList<FoodItem>
//
//                val dailyMealData = DailyMealData()
//                dailyMealData.date = "01-10-2021"
//                dailyMealData.
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    dao.insertToMealData(dailyMealData)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        foodItemRef.addValueEventListener(dataListener)
//    }

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