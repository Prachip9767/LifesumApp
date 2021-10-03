package com.example.lifesum.repositary

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class Repo(private val dao: DAO) {
    private val fsRoot = FirebaseFirestore.getInstance()
    private val userUID = FirebaseAuth.getInstance().currentUser?.uid
    private val userRef = fsRoot.collection("Users").document(userUID!!)
    private val dashboardRef = fsRoot.collection("Users").document(userUID!!)
        .collection("Dashboard")
    private val mealRecordRef = fsRoot.collection("Users").document(userUID!!)
        .collection("MealRecords")

    fun addMealRecordsToServer(date: String, record: DailyMealData) {
        //mealRecordRef.document(date).collection("records").document(id.toString()).set(record)
    }

    fun insertMealRecordsToDB(mealRecord: DailyMealData) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertToMealData(mealRecord)
        }
    }

    fun getMealRecordsFromServer() {
        mealRecordRef.addSnapshotListener { snapshot, e ->

            if (snapshot != null && !snapshot.isEmpty) {
                val mealRcList = snapshot.documents
                for (doc in mealRcList) {
                    val record = doc.toObject(DailyMealData::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.insertToMealData(record!!)
                    }
                }
            } else {
                Log.d("rkpsx7", "Failed To get meal records from server")
            }

        }
    }


    fun getUserMealRecordFromDB(date: String, type: String): LiveData<DailyMealData> {
        return dao.getMealData(date, type)
    }


    fun addUserDetailsToServer(user: UserEntity) {
        fsRoot.collection("Users").document(userUID!!).set(user)
    }

    fun addDashboardDataToServer(dsbData: DashboardEntity, date: String) {
        dashboardRef.document(date).set(dsbData)
    }


    fun getDashboardDataFromServer() {
        dashboardRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                "Local"
            else
                "Server"

            if (snapshot != null && !snapshot.isEmpty) {
                val dshList = snapshot.documents
                for (i in dshList) {
                    val dsh = i.toObject(DashboardEntity::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.insertToDashBoard(dsh!!)
                    }
                }
            } else {
                Log.d("rkpsx7", "$source data: null")
            }
        }
    }

    fun getDashboardDataFromDB(date: String): LiveData<DashboardEntity> {
        return dao.getDashboardDataFromDb(date)
    }

    fun getUserDetailsFromDB(): LiveData<UserEntity> {
        return dao.getUserFromDB()
    }


    fun getUserDetailsFromServer() {
        userRef.get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.getString("name_of_user").toString()
                val email = it.getString("email").toString()
                val goalType = it.get("goalType").toString().toInt()
                val gender = it.getString("gender").toString()
                val b_date = it.get("b_date").toString().toInt()
                val b_month = it.getString("b_month").toString()
                val b_year = it.get("b_year").toString().toInt()
                val height = it.get("height").toString().toInt()
                val curr_weight = it.get("curr_weight").toString().toInt()
                val goal_weight = it.get("goal_weight").toString().toInt()

                val user = UserEntity(
                    name, email,
                    goalType, gender, b_date, b_month, b_year, height, curr_weight, goal_weight
                )
                CoroutineScope(Dispatchers.IO).launch {
                    dao.insertUserDetails(user)
                }
            }
        }
    }


}