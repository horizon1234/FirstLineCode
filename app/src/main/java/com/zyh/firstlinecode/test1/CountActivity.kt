package com.zyh.firstlinecode.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.zyh.firstlinecode.R
import kotlinx.android.synthetic.main.activity_count.*

class CountActivity : AppCompatActivity() {

    private lateinit var viewModel: CountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        /**
         * 对于class文件，进行区分了kclass和class，比如要求的class时必须要xx::class.java
         * 否则就是xx::classs
         *
         * ViewModel的实例不能直接去创建，因为viewmodel有其独立的生命周期，其长于activity，
         * 如果在onCreate里创建实例的话，在手机屏幕发生旋转时，会跑onCreate，就无法保留数据了
         * */
        viewModel = ViewModelProviders.of(this).get(CountModel::class.java)
        /**
         * object代表匿名内部类 实例而已，而且kotlin写这种代码时可以提示简化
         * */
        plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        refreshCounter()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}
