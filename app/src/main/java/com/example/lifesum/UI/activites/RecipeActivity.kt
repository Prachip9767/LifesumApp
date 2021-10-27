package com.example.lifesum.UI.activites

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_recipe)

        //setToViews()

    }

    private fun setToViews() {
        val img_url = intent.getStringExtra("img_url")
        val item_name = intent.getStringExtra("item_name")
        val kcal = intent.getStringExtra("kcal")
        val protein = intent.getStringExtra("protein")
        val sugars = intent.getStringExtra("sugars")
        val fiber = intent.getStringExtra("fiber")
        val sat_fat = intent.getStringExtra("sat_fat")?.toDouble()
        val unSat_fat = intent.getStringExtra("unSat_fat")?.toDouble()
        val fat = (sat_fat?.plus(unSat_fat!!))

    }


}
