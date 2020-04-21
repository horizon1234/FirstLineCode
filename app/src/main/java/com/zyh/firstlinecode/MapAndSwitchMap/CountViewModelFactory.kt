package com.zyh.firstlinecode.MapAndSwitchMap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountModel(countReserved) as T
    }

}
