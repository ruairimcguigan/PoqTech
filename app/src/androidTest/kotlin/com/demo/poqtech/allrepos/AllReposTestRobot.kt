package com.demo.poqtech.allrepos

import androidx.annotation.IntegerRes
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.rule.ActivityTestRule
import com.demo.poqtech.testrobot.TestRobot
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Rule

open class AllReposTestRobot : TestRobot() {

    @get:Rule
    val activityRule = ActivityTestRule(
        AllReposActivity::class.java,
        true,
        false
    )

    fun allRepos(func: TestRobot.() -> Unit) = TestRobot().apply { func() }

    private val mockWebServer = MockWebServer()

    internal fun startServer() = mockWebServer.start(port = 8080)

    internal fun launchActivity() = activityRule.launchActivity(null)

    internal fun getApiResponse(responseCode: Int, responseBody: String) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(responseCode)
                    .setBody(responseBody)
            }
        }
    }

    internal fun verifyAllReposViewState(
        vararg viewStates: Pair<Int, Visibility>
    ) = verifyViewStatesVisibility(*viewStates)

    fun verifyCorrectErrorMessageShown(
        @IntegerRes viewId: Int, errorValue: String) = matchText(viewId, errorValue
    )

    fun verifyGeneralErrorShown(
        @IntegerRes viewId: Int, errorValue: String) = matchText(viewId, errorValue
    )

    internal fun stopServer() = mockWebServer.shutdown()
}
