package com.example.lifesum.UI.activites

import android.os.Bundle
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
        val sat_fat = intent.getStringExtra("sat_fat")
        val unSat_fat = intent.getStringExtra("unSat_fat")


        Glide.with(ivItemImage).load(img_url).into(ivItemImage)
        tv_item_name_details.text = item_name
        tv_kcal_details.text = kcal
        tv_protein_details.text = protein
        tv_carbs_details.text = ""
        tv_fiber_details.text = fiber
        tv_sugars_details.text = sugars
        tv_fat_details.text = ""
        tv_sat_fat_details.text = sat_fat
        tv_unSat_fat_details.text = unSat_fat
    }
}