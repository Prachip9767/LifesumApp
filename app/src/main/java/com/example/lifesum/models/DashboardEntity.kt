package com.example.lifesum.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DashBoardTable")
class DashboardEntity(
    @PrimaryKey
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "eaten") var eaten: Int,
    @ColumnInfo(name = "kacl") var kacl: Int,
    @ColumnInfo(name = "burned") var burned: Int,
    @ColumnInfo(name = "carbs") var carbs: Int,
    @ColumnInfo(name = "protein") var protein: Int,
    @ColumnInfo(name = "fat") var fat: Int,
    @ColumnInfo(name = "water_glass") var water_glass: Int,
)
