package com.zyh.firstlinecode.viewModel

import androidx.lifecycle.ViewModel

class CountModel(countReserved: Int) : ViewModel() {

    var counter = countReserved
}