package com.demo.poqtech

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.demo.poqtech.allrepos.AllReposActivity
import com.demo.poqtech.data.api.OkHttpProvider
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllReposActivityTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(
        AllReposActivity::class.java,
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

        activityRule.launchActivity(null)

        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(GONE)))
    }

    @Test
    fun testFailedIResourceMovedErrorResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(301)
                    .setBody("")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withText("Requested resource has been changed permanently")))
    }

    @Test
    fun testFailedResourceForbiddenResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(403)
                    .setBody("")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withText("Requested resource is forbidden for some reason")))
    }

    @Test
    fun testFailedNotFoundResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(404)
                    .setBody("")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withText("Received Not found 404 error")))
    }

    @Test
    fun testFailedInternalErrorResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(500)
                    .setBody("")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withText("Internal server error, please try again later")))
    }

    @Test
    fun testFailedBadGatewayResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(502)
                    .setBody("")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.noReposView)).check(matches(withText("Bad response from that proxy server")))
    }

    @Test
    fun testGeneralError() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(204)
                    .setBody("{}")
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.repoListView)).check(matches(withEffectiveVisibility(GONE)))
        onView(withId(R.id.noReposView)).check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("HTTP 204 had non-zero Content-Length: 2")))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}