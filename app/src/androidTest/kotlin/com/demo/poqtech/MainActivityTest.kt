package com.demo.poqtech

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(
        MainActivity::class.java,
        true,
        false
    )

    @Before
    fun setup() {
        mockWebServer.start(port = 8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}