package com.example.lifesum.UI.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var roomDB: MainRoomDB
    private lateinit var dao: DAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        roomDB = MainRoomDB.getMainRoomDb(this)
        dao = roomDB.getDao()


//        dao.getUserFromDB().observe(this, {
//            tvCurrWeightS.text = it.curr_weight.toString()
//            tvGoalWeightS.text = it.goal_weight.toString()
//            tvHeightInS.text = it.height.toString()
//            val dob = "${it.b_date}-${it.b_month}-${it.b_year}"
//            DOBS.text = dob
//            tvGenderS.text = it.gender
//        })


    }
}