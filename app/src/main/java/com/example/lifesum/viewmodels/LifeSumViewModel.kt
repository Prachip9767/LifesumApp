package com.example.lifesum.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lifesum.models.DashboardEntity
import com.example.lifesum.models.UserEntity
import com.example.lifesum.repositary.Repo


class LifeSumViewModel(val repo: Repo) : ViewModel() {

    fun addUserDetailsToServer(user: UserEntity) {
        repo.addUserDetailsToServer(user)
    }

    fun addDashboardDataToServer(dsbData: DashboardEntity) {
        repo.addDashboardDataToServer(dsbData)
    }
//
    fun getUserDetailsFromServer() {
        repo.getUserDetailsFromServer()
    }

    fun getUserDetailsFromDB(): LiveData<UserEntity> {
        return repo.getUserDetailsFromDB()
    }

    fun getDashboardDataFromServer() {
        repo.getDashboardDataFromServer()
    }

    fun getDashboardDataFromDb(date: String): LiveData<DashboardEntity> {
        return repo.getDashboardDataFromDB(date)
    }

}