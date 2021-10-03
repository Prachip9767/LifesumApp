package com.example.lifesum.LocalDatabase

import com.example.lifesum.models.FoodItem

interface onMealSearchItemClicked {
    fun onMealSearchItemClicked(item: FoodItem)
    fun onMealRecordItemClicked(item: FoodItem)
}