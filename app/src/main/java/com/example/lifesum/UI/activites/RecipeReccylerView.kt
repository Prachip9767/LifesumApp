package com.example.lifesum.UI.activites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lifesum.LocalDatabase.onMealSearchItemClicked
import com.example.lifesum.R
import com.example.lifesum.UI.adapters.RecipeAdapter
import com.example.lifesum.models.FoodItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recipe_reccyler_view.*

class RecipeReccylerView : AppCompatActivity(), onMealSearchItemClicked {
    private lateinit var fb_db: FirebaseDatabase
    private lateinit var foodItemRef: DatabaseReference
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipeList = ArrayList<FoodItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_reccyler_view)
        fb_db = FirebaseDatabase.getInstance()
        foodItemRef = fb_db.getReference("FoodItems")
        getItemsFromServer()



    }

    private fun getItemsFromServer() {
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map { it.getValue(FoodItem::class.java)!! }
                recipeList.clear()
                recipeList = items as ArrayList<FoodItem>
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        foodItemRef.addValueEventListener(dataListener)

    }

    private fun setAdapter() {
        recipeAdapter = RecipeAdapter(recipeList, this)
        recipeRecyclerView.adapter = recipeAdapter
        recipeRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onRecipeItemClick(item: FoodItem) {
        val intent = Intent(this, RecipeActivity::class.java)
        startActivity(intent)
    }

    override fun onMealSearchItemClicked(item: FoodItem) {

    }

    override fun onMealRecordItemClicked(item: FoodItem) {

    }


}