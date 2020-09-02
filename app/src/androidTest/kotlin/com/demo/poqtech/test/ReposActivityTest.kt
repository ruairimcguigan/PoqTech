package com.demo.poqtech.test

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.demo.poqtech.data.api.OkHttpProvider
import com.demo.poqtech.util.ResponseReader
import com.demo.poqtech.allrepos.ReposActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After

import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class ReposActivityTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(
        ReposActivity::class.java,
        true,
        false
    )

    @Before
    fun setup() {
        mockWebServer.start(port = 8080)

        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            ))
    }

    @Test
    fun testSuccessfulMockedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(ResponseReader.readJsonFromFile("mocked_success_response.json"))
            }
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}