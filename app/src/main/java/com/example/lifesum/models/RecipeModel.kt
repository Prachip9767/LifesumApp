package com.example.lifesum.models

class RecipeModel(
    var item_name: String = "",
    var fiber: Double = 0.0,
    var img_url: String = "",
    var ingredients: String = "",
    var instructions: String = "",
    var kcal: Double = 0.0,
    var make_time: Int = 0,
    var protein: Double = 0.0,
    var saturated_fat: Double = 0.0,
    var unsaturated_fat: Double = 0.0,
    var sugar: Double = 0.0
){}