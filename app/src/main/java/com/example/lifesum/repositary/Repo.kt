package com.example.lifesum.repositary

import androidx.lifecycle.LiveData
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repo(private val dao: DAO) {
    private val fsRoot = FirebaseFirestore.getInstance()
    private val userUID = FirebaseAuth.getInstance().uid
    private val userRef = fsRoot.collection("Users").document(userUID!!)
    private val dashboardRef = fsRoot.collection("Users").document(userUID!!)
        .collection("Dashboard").document(userUID)

    fun addUserDetailsToServer(user: UserEntity) {
        fsRoot.collection("Users").document(userUID!!).set(user)
    }

    fun addDashboardDataToServer(dsbData: DashboardEntity) {
        fsRoot.collection("Users").document(userUID!!)
            .collection("Dashboard").document(userUID).set(dsbData)
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

    fun getDashboardDataFromServer() {
        dashboardRef.get().addOnSuccessListener {
            if (it.exists()) {
                val date = it.getString("date").toString()
                val burned = it.get("burned").toString().toInt()
                val carbs = it.get("carbs").toString().toInt()
                val eaten = it.get("eaten").toString().toInt()
                val fat = it.get("fat").toString().toInt()
                val kacl = it.get("kacl").toString().toInt()
                val protein = it.get("protein").toString().toInt()
                val water_glass = it.get("water_glass").toString().toInt()

                val dashboard = DashboardEntity(
                    date, eaten, kacl, burned, carbs, protein, fat, water_glass
                )
                CoroutineScope(Dispatchers.IO).launch {
                    dao.insertToDashBoard(dashboard)
                }

            }
        }
    }

    fun getUserDetailsFromDB(): LiveData<UserEntity> {
        return dao.getUserFromDB()
    }


}