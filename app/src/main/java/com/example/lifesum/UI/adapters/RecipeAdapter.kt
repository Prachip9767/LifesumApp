package com.example.lifesum.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lifesum.LocalDatabase.onMealSearchItemClicked
import com.example.lifesum.R
import com.example.lifesum.models.FoodItem
import kotlinx.android.synthetic.main.item_layout.view.*

class RecipeAdapter(
    var dataList: ArrayList<FoodItem>,
    val onMealSearchItemClicked: onMealSearchItemClicked
) : RecyclerView.Adapter<RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = dataList[position]
        holder.setData(item)
        holder.clickOnRecipe.setOnClickListener {
            onMealSearchItemClicked.onRecipeItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}

class RecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val clickOnRecipe: LinearLayout = view.findViewById(R.id.clickOnRecipe)
    fun setData(reciIem: FoodItem) {
        view.apply {
            Glide.with(ivItemImage_r).load(reciIem.img_url).into(ivItemImage_r)
            tvItemName_r.text = reciIem.item_name
            tvKcal_r.text = "${reciIem.kcal.toString()} KCAL"
        }
    }


}