package com.example.lifesum.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lifesum.R
import com.example.lifesum.models.FoodItem
import kotlinx.android.synthetic.main.meal_search_item_layout.view.*

class MealSearchAdapter(var dataList: ArrayList<FoodItem>) :
    RecyclerView.Adapter<MealSearchViewHolder>() {
    var backupList = ArrayList<FoodItem>()

    init {
        backupList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_search_item_layout, parent, false)
        return MealSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealSearchViewHolder, position: Int) {
        val item = dataList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filteredList = ArrayList<FoodItem>()
                val keyword = p0.toString()
                if (keyword.isEmpty()) {
                    filteredList = backupList
                } else {

                    for (obj in backupList) {
                        if (obj.item_name.lowercase().contains(keyword.lowercase().trim())&&
                                obj.type.lowercase().contains("simple")) {
                            filteredList.add(obj)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                dataList = p1?.values as ArrayList<FoodItem>
                notifyDataSetChanged()
            }

        }
    }


}

class MealSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(mealItem: FoodItem) {
        itemView.apply {
            Glide.with(iv_search_meal_image).load(mealItem.img_url).into(iv_search_meal_image)
            tv_search_item_name.text = mealItem.item_name
            tv_search_kacl.text = mealItem.kcal.toString()
        }
    }
}
