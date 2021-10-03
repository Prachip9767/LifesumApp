package com.example.lifesum.models

class FoodItem(
    val img_url: String = "",
    val item_name: String = "",
    var make_time: Int = 0,
    val kcal: Double = 0.0,
    val protein: Double = 0.0,
    val fiber: Double = 0.0,
    val sugars: Double = 0.0,
    val saturated_fat: Double = 0.0,
    val unsaturated_fat: Double = 0.0,
    var ingredients: String = "",
    var instructions: String = "",
    var type: String = ""

)