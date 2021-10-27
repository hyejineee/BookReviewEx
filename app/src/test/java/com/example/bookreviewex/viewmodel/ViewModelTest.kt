package com.example.bookreviewex.viewmodel

import com.example.bookreviewex.di.testModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

internal abstract class ViewModelTest:KoinTest{

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testModules)
    }

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}