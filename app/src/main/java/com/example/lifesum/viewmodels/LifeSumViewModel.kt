package com.example.lifesum.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lifesum.models.DailyMealData
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.UserEntity
import com.example.lifesum.repositary.Repo


class LifeSumViewModel(val repo: Repo) : ViewModel() {

    fun addUserDetailsToServer(user: UserEntity) {
        repo.addUserDetailsToServer(user)
    }

    fun addMealRecordsToServer(date: String,record: DailyMealData) {
        repo.addMealRecordsToServer(date,  record)
    }

    fun getUserMealRecordFromDB(date: String, type: String): LiveData<DailyMealData> {
        return repo.getUserMealRecordFromDB(date, type)
    }

    fun insertMealRecordsToDB(mealRecord: DailyMealData) {
        repo.insertMealRecordsToDB(mealRecord)
    }

    fun getMealRecordsFromServer() {
        repo.getMealRecordsFromServer()
    }

    fun addDashboardDataToServer(dsbData: DashboardEntity, date: String) {
        repo.addDashboardDataToServer(dsbData, date)
    }

    fun addDashboardDataToServer(date: String, dsbData: DashboardEntity) {
        repo.addDashboardDataToServer(date, dsbData)
    }

    fun getUserDetailsFromServer() {
        repo.getUserDetailsFromServer()
    }

    fun getUserDetailsFromDB(): LiveData<UserEntity> {
        return repo.getUserDetailsFromDB()
    }

    fun getDashboardDataFromServer() {
        repo.getDashboardDataFromServer()
    }

    fun updateDashBoardInDB(dsh: DashboardEntity) {
        repo.updateDashBoardInDB(dsh)
    }

    fun getDashboardDataFromDb(date: String): LiveData<DashboardEntity> {
        return repo.getDashboardDataFromDB(date)
    }

}