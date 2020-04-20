package com.zyh.firstlinecode.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountModel(countReserved: Int) : ViewModel() {
    /**
     * 这里不能直接爆了可变的livedata给外部,做法是这里定义了一个counter，它的类型是Livedata
     * 重写了get方法而已
     * */
    val counter : LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        /**
         * kotlin的?:符号
         * 相当于counter==null时取后者
         * 和?.是一样的
         * */
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }
}