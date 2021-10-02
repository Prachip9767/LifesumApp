package com.example.lifesum.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "DailyMealDataTable")
class DailyMealData(
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "Item") var item: List<FoodItem>
) {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null
}

class FoodItemTypeConverter {
    @TypeConverter
    fun objToJson(value: List<FoodItem>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToObj(value: String) = Gson().fromJson(value, Array<FoodItem>::class.java).toList()
}