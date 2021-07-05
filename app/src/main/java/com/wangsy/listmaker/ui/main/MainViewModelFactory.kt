package com.wangsy.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// 1
class MainViewModelFactory(private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {

    // 2
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(sharedPreferences) as T
    }
}
