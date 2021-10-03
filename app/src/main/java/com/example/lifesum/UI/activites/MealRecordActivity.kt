package com.example.lifesum.UI.activites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifesum.LocalDatabase.DAO
import com.example.lifesum.LocalDatabase.MainRoomDB
import com.example.lifesum.LocalDatabase.onMealSearchItemClicked
import com.example.lifesum.R
import com.example.lifesum.UI.adapters.MealRecordAdapter
import com.example.lifesum.UI.adapters.MealSearchAdapter
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.FoodItem
import com.example.lifesum.repositary.Repo
import com.example.lifesum.viewmodels.LifeSumViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_meal_record.*

class MealRecordActivity : AppCompatActivity(), onMealSearchItemClicked {

    private var searchDataList = ArrayList<FoodItem>()
    private var mealRecordDataList = ArrayList<FoodItem>()

    private lateinit var mealRecordAdapter: MealRecordAdapter

    private lateinit var roomDB: MainRoomDB
    private lateinit var dao: DAO
    private lateinit var repo: Repo
    private lateinit var viewModel: LifeSumViewModel

    private lateinit var searchMealAdapter: MealSearchAdapter
    private var curr_date: String = ""

    private var foodType: String = ""
    private lateinit var fb_db: FirebaseDatabase
    private lateinit var foodItemRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_record)
        initMV()
        setUpActivity()
        setAdapter()
        getUserMealData()

        getItemsFromServer()

    }


    private fun getUserMealData() {
        viewModel.getUserMealRecordFromDB(curr_date, foodType).observe(this, Observer {
            val itemsList = it?.item
            Log.d("rkpsx7", itemsList?.size.toString())
            if (itemsList != null) {
                mealRecordDataList.clear()
                mealRecordDataList.addAll(itemsList)
            }
            mealRecordAdapter.notifyDataSetChanged()
        })
    }

    private fun setAdapter() {
        mealRecordAdapter = MealRecordAdapter(mealRecordDataList, this)
        recyclerView_meal_record.adapter = mealRecordAdapter
        recyclerView_meal_record.layoutManager = LinearLayoutManager(this)
    }

    override fun onMealSearchItemClicked(item: FoodItem) {
        mealRecordDataList.add(item)
        val recordMeal = DailyMealData(curr_date, foodType, mealRecordDataList)

        viewModel.insertMealRecordsToDB(recordMeal)
        //viewModel.addMealRecordsToServer(curr_date, recordMeal)
        toast("Added to Track records")

    }

    override fun onMealRecordItemClicked(item: FoodItem) {
        val intent = Intent(this, FoodDetailsActivity::class.java)
        intent.putExtra("img_url", item.img_url)
        intent.putExtra("item_name", item.item_name)
        intent.putExtra("kcal", item.kcal)
        intent.putExtra("protein", item.protein)
        intent.putExtra("fiber", item.fiber)
        intent.putExtra("sugars", item.sugars)
        intent.putExtra("sat_fat", item.saturated_fat)
        intent.putExtra("unSat_fat", item.unsaturated_fat)
        startActivity(intent)
    }


    private fun getSearchReady() {
        searchMealAdapter = MealSearchAdapter(searchDataList, this)
        recyclerViewSearchFood.adapter = searchMealAdapter
        recyclerViewSearchFood.layoutManager = LinearLayoutManager(this)

        etMealSearchBar.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0?.length!! > 0) {
                    recyclerViewSearchFood.visibility = View.VISIBLE
                    recyclerView_meal_record.visibility = View.GONE
                } else {
                    recyclerViewSearchFood.visibility = View.GONE
                    recyclerView_meal_record.visibility = View.VISIBLE
                }
                searchMealAdapter.getFilter().filter(p0)
                return true
            }

        })
    }

    private fun getItemsFromServer() {
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
                searchDataList.clear()
                searchDataList = items as ArrayList<FoodItem>
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

        roomDB = MainRoomDB.getMainRoomDb(this)
        dao = roomDB.getDao()
        repo = Repo(dao)
        viewModel = LifeSumViewModel(repo)

        fb_db = FirebaseDatabase.getInstance()
        foodItemRef = fb_db.getReference("FoodItems")
        foodType = intent.getStringExtra("foodType").toString()
        curr_date = intent.getStringExtra("curr_date").toString()


    }

    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}