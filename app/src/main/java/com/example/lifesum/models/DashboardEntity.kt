package com.example.lifesum.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DashBoardTable")
class DashboardEntity(
    @PrimaryKey
    @ColumnInfo(name = "date") var date: String = "",
    @ColumnInfo(name = "eaten") var eaten: Int = 0,
    @ColumnInfo(name = "kacl") var kacl: Int = 0,
    @ColumnInfo(name = "burned") var burned: Int = 0,
    @ColumnInfo(name = "carbs") var carbs: Int = 0,
    @ColumnInfo(name = "protein") var protein: Int = 0,
    @ColumnInfo(name = "fat") var fat: Int = 0,
    @ColumnInfo(name = "water_glass") var water_glass: Int = 0,
)
