package com.example.lifesum.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lifesum.LocalDatabase.onMealSearchItemClicked
import com.example.lifesum.R
import com.example.lifesum.models.FoodItem
import kotlinx.android.synthetic.main.meal_recoed_item_layout.view.*

class MealRecordAdapter(
    val dataList: ArrayList<FoodItem>,
    val onMealRecordItemClicked: onMealSearchItemClicked
) :
    RecyclerView.Adapter<MealRecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_recoed_item_layout, parent, false)
        return MealRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealRecordViewHolder, position: Int) {
        val item = dataList[position]
        holder.setData(item)
        holder.productCardView.setOnClickListener {
            onMealRecordItemClicked.onMealRecordItemClicked(item)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class MealRecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val productCardView: ConstraintLayout = view.findViewById(R.id.productCardView)
    fun setData(foodItem: FoodItem) {
        itemView.apply {
            Glide.with(iv_meal_image).load(foodItem.img_url).into(iv_meal_image)
            tv_meal_record_item_name.text = foodItem.item_name
            tv_meal_rec__kacl.text = foodItem.kcal.toString()

        }
    }
}