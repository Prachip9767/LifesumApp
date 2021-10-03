package com.example.lifesum.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesum.R

class RecipesAdapter (val addModel: List<Recipes>, val recipesList: MutableList<Recipes>) :
        RecyclerView.Adapter<ItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ItemHolder(view)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {

            var addModel:Recipes = recipesList[position]
            holder.tvName.text=addModel.name
           holder.tvcalori.text=addModel.calori

        }

        override fun getItemCount(): Int {
            return recipesList.size
        }
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvName: TextView = view.findViewById(R.id.tvItemName)
        var tvImage: ImageView = view.findViewById(R.id.ivItem)
        var tvcalori:TextView=view.findViewById(R.id.tvKcal)

    }