package com.zyh.firstlinecode.MapAndSwitchMap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CountModel(countReserved: Int) : ViewModel() {
    /**
     * 这里不能直接爆了可变的livedata给外部,做法是这里定义了一个counter，它的类型是Livedata
     * 重写了get方法而已
     * */
    val counter: LiveData<Int>
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

    fun clear() {
        _counter.value = 0
    }

    private val userLiveData = MutableLiveData<User>()
    /**
     * 这里的写法就是转换，把User类型转成String类型，而其中第二个参数是一个函数
     * 也是返回值的操作，当第二个参数是接口类型可以放在外面一层写
     * 同理这里的LiveData也是不变的 为了防止外部修改
     * */
    val userName: LiveData<String> = Transformations.map(userLiveData) { input: User ->
        "${input.firstName}${input.lastName}"
    }

    /**
     * 如果我这里的错误写法如下
     * */

    fun getUsrE(userId: String):LiveData<User>{
        return Repository.getUser(userId)
    }
    //这样写是没有问题的，是每次都会调用，返回一个实例
    //但是我在activity中如果直接viewModel.getUser(userId).observe这种写法，只在我点击获取的时候，
    //第一次这个值是对的，如果我其他地方调用了这个，就不行了 他一直观察的是老的

    /**
     * 下面这种写法，就比较科学，就一直有一个user对象，专门是用来被观察的
     * 然后getUser方法，想啥时候调用，就啥事调用
     * 然后通过switchMap可以把一个liveData对象转成另一个liveData对象，
     * 当然会自动观察userIdLiveData
     *
     * 这里还有个很骚的地方，比如我getUser没有参数，这个时候怎么搞呢
     * 可以使用userIdLiveData.value = userIdLiveData.value这种办法，自己给自己赋值，也可以
     * 触发
     * */

    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId:String){
        userIdLiveData.value = userId
    }
}