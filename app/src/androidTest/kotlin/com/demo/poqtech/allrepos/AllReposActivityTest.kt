package com.demo.poqtech.allrepos

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.poqtech.R
import com.demo.poqtech.data.api.OkHttpProvider
import com.demo.poqtech.util.ResponseReader.readJson
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllReposActivityTest : AllReposTestRobot(){

    @Before
    fun setup() {

        startServer()

        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            ))
    }

    @Test
    fun testSuccessfulMockedResponse() {

        allRepos {
            // given
            val repoList = Pair(R.id.repoListView, VISIBLE)
            val noRepos = Pair(R.id.noReposView, GONE)

            getApiResponse(
                responseCode = 200,
                responseBody = readJson("mocked_success_response.json")
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                repoList,
                noRepos
            )
        }
    }

    @Test
    fun testFailedIResourceMovedErrorResponse() {

        allRepos {

            // given
            val progressBar = Pair(R.id.progressBar, GONE)
            val repoListView = Pair(R.id.repoListView, GONE)
            val noReposView = Pair(R.id.noReposView, VISIBLE)

            getApiResponse(
                responseCode = 301,
                responseBody = ""
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )

            verifyCorrectErrorMessageShown(
                viewId = R.id.noReposView,
                errorValue = "Requested resource has been changed permanently"
            )
        }
    }

    @Test
    fun testFailedResourceForbiddenResponse() {

        allRepos {

            // given
            val progressBar = Pair(R.id.progressBar, GONE)
            val repoListView = Pair(R.id.repoListView, GONE)
            val noReposView = Pair(R.id.noReposView, VISIBLE)

            getApiResponse(
                responseCode = 403,
                responseBody = ""
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )

            verifyCorrectErrorMessageShown(
                viewId = R.id.noReposView,
                errorValue = "Requested resource is forbidden for some reason"
            )
        }
    }

    @Test
    fun testFailedNotFoundResponse() {

        allRepos {

            // given
            val progressBar = Pair(R.id.progressBar, GONE)
            val repoListView = Pair(R.id.repoListView, GONE)
            val noReposView = Pair(R.id.noReposView, VISIBLE)

            getApiResponse(
                responseCode = 404,
                responseBody = ""
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )

            verifyCorrectErrorMessageShown(
                viewId = R.id.noReposView,
                errorValue = "Received Not found 404 error"
            )
        }
    }

    @Test
    fun testBadResponseErrorResponse() {

        allRepos {

            // given
            val progressBar = Pair(R.id.progressBar, GONE)
            val repoListView = Pair(R.id.repoListView, GONE)
            val noReposView = Pair(R.id.noReposView, VISIBLE)

            getApiResponse(
                responseCode = 502,
                responseBody = ""
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )

            verifyCorrectErrorMessageShown(
                viewId = R.id.noReposView,
                errorValue = "Bad response from that proxy server"
            )
        }
    }

    @Test
    fun testInternalErrorResponse() {

        val progressBar = Pair(R.id.progressBar, GONE)
        val repoListView = Pair(R.id.repoListView, GONE)
        val noReposView = Pair(R.id.noReposView, VISIBLE)

        allRepos {

            // given
            getApiResponse(
                responseCode = 500,
                responseBody = ""
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )
            verifyCorrectErrorMessageShown(
                viewId = R.id.noReposView,
                errorValue = "Internal server error, please try again later"
            )
        }
    }


    @Test
    fun testGeneralErrorUsingSnackBar() {

        allRepos {

            // given
            val progressBar = Pair(R.id.progressBar, GONE)
            val repoListView = Pair(R.id.repoListView, GONE)
            val noReposView = Pair(R.id.noReposView, VISIBLE)

            getApiResponse(
                responseCode = 204,
                responseBody = "{}"
            )

            // when
            launchActivity()

            // then
            verifyAllReposViewState(
                progressBar,
                repoListView,
                noReposView
            )

            verifyGeneralErrorShown(
                viewId = com.google.android.material.R.id.snackbar_text,
                errorValue = "HTTP 204 had non-zero Content-Length: 2"
            )
        }
    }

    @After
    fun teardown() = stopServer()
}