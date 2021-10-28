package com.example.bookreviewex.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.bookreviewex.di.testModules
import com.example.bookreviewex.util.LivedataTestObserver
import com.example.bookreviewex.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@ExperimentalCoroutinesApi
internal abstract class ViewModelTest:KoinTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testModules)
    }

    @get:Rule
    val coroutineRule = MainCoroutineRule(TestCoroutineDispatcher())

    protected fun <T> LiveData<T>.test():LivedataTestObserver<T>{
        val testObserver = LivedataTestObserver<T>()
        this.observeForever(testObserver)
        return testObserver
    }
}