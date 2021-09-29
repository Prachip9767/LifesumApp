package com.example.lifesum.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class UserEntity(
    @ColumnInfo(name = "name_of_user") var name_of_user: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "goalType") var goalType: Int,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(name = "b_date") var b_date: Int,
    @ColumnInfo(name = "b_month") var b_month: String,
    @ColumnInfo(name = "b_year") var b_year: Int,
    @ColumnInfo(name = "height") var height: Int,
    @ColumnInfo(name = "curr_weight") var curr_weight: Int,
    @ColumnInfo(name = "goal_weight") var goal_weight: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}