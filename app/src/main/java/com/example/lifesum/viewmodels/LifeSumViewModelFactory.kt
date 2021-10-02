package com.example.lifesum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lifesum.repositary.Repo

class LifeSumViewModelFactory(val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LifeSumViewModel(repo) as T
    }
}