package com.zyh.firstlinecode.livedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zyh.firstlinecode.R
import kotlinx.android.synthetic.main.activity_count.*

class CountActivity : AppCompatActivity() {

    private lateinit var viewModel: CountModel
    private lateinit var sp: SharedPreferences
    private val KEY_COUNT = "count_reversed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(KEY_COUNT, 0)
        viewModel = ViewModelProviders.of(this, CountViewModelFactory(countReserved))
            .get(CountModel::class.java)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        /**
         * 对于class文件，进行区分了kclass和class，比如要求的class时必须要xx::class.java
         * 否则就是xx::classs
         *
         * ViewModel的实例不能直接去创建，因为viewmodel有其独立的生命周期，其长于activity，
         * 如果在onCreate里创建实例的话，在手机屏幕发生旋转时，会跑onCreate，就无法保留数据了
         * */
//        viewModel = ViewModelProviders.of(this).get(CountModel::class.java)
        /**
         * object代表匿名内部类 实例而已，而且kotlin写这种代码时可以提示简化
         * */
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        viewModel.counter.observe(this, Observer { count ->
            infoText.text = count.toString()
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(KEY_COUNT, viewModel.counter.value ?: 0)
        }
    }
}
