package com.example.lifesum.UI.activites

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifesum.R
import com.example.lifesum.UI.adapters.MealSearchAdapter
import com.example.lifesum.models.FoodItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_meal_record.*

class MealRecordActivity : AppCompatActivity() {

    private var searchDataList = ArrayList<FoodItem>()
    private lateinit var searchMealAdapter: MealSearchAdapter

    private var foodType: String = ""
    private lateinit var fb_db: FirebaseDatabase
    private var uid: String? = null
    private lateinit var foodItemRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_record)
        initMV()
        setUpActivity()
        getItemsFromServer()


    }

    private fun getSearchReady() {
        searchMealAdapter = MealSearchAdapter(searchDataList)
        Log.d("rkpsx7", searchDataList.size.toString() + " in size")
        recyclerViewSearchFood.adapter = searchMealAdapter
        recyclerViewSearchFood.layoutManager = LinearLayoutManager(this)

//        if (etMealSearchBar.isSelected)
//            recyclerViewSearchFood.visibility = View.VISIBLE
//        else
//            recyclerViewSearchFood.visibility = View.GONE

        etMealSearchBar.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchMealAdapter.getFilter().filter(p0)
                return true
            }

        })


    }

    private fun getItemsFromServer() {
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
                Log.d("rkpsx7", items.size.toString() + " in size -x")
                searchDataList.clear()
                searchDataList = items as ArrayList<FoodItem>
                Log.d("rkpsx7", searchDataList.size.toString() + " in size -x")
                // if (searchDataList.size > 0)
                getSearchReady()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MealRecordActivity, "Try again", Toast.LENGTH_SHORT).show()
            }
        }

        foodItemRef.addValueEventListener(dataListener)


    }

    private fun setUpActivity() {
        tv_act_type_header.text = foodType
    }

    private fun initMV() {
        fb_db = FirebaseDatabase.getInstance()
        foodItemRef = fb_db.getReference("FoodItems")
        foodType = intent.getStringExtra("foodType").toString()
    }

}