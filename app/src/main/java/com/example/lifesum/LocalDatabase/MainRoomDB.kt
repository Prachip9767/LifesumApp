package com.example.lifesum.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.FoodItemTypeConverter
import com.example.lifesum.models.UserEntity

@Database(entities = [UserEntity::class, DashboardEntity::class, DailyMealData::class], version = 1)
@TypeConverters(FoodItemTypeConverter::class)
abstract class MainRoomDB : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object {
        private var Instance: MainRoomDB? = null

        fun getMainRoomDb(context: Context): MainRoomDB {
            return if (Instance == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    MainRoomDB::class.java,
                    "LifeSumDB"
                )
                builder.fallbackToDestructiveMigration()
                Instance = builder.build()
                Instance!!
            } else Instance!!
        }
    }
}