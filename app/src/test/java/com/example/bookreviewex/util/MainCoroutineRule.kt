package com.example.bookreviewex.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher:TestCoroutineDispatcher
):TestWatcher() {


    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.setMain(dispatcher)

    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.resetMain()
    }
}