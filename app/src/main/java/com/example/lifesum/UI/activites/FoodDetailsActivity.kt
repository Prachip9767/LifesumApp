package com.example.lifesum.UI.activites

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_food_details.*

class FoodDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        setDetails()
    }

    private fun setDetails() {
        val img_url = intent.getStringExtra("img_url")
        val item_name = intent.getStringExtra("item_name")
        val kcal = intent.getStringExtra("kcal")
        val protein = intent.getStringExtra("protein")
        val sugars = intent.getStringExtra("sugars")
        val fiber = intent.getStringExtra("fiber")
        val sat_fat = intent.getStringExtra("sat_fat")?.toDouble()
        val unSat_fat = intent.getStringExtra("unSat_fat")?.toDouble()
        val fat = (sat_fat?.plus(unSat_fat!!))
        Log.d("rkpsx7", img_url.toString())
        Glide.with(ivItemImage).load(img_url).into(ivItemImage)
        tv_item_name_details.text = item_name
        tv_kcal_details.text = "$kcal kcal"
        tv_protein_details.text = "$protein g"
        tv_carbs_details.text = "${fiber + sugars}"
        Log.d("rkpsx7", protein.toString())
        tv_fiber_details.text = "$fiber g"
        tv_sugars_details.text = "$sugars g"
        tv_fat_details.text = "${fat.toString()} g"
        tv_sat_fat_details.text = "${sat_fat.toString()} g"
        tv_unSat_fat_details.text = "${unSat_fat.toString()} g"
    }
}