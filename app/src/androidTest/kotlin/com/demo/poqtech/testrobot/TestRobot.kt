package com.demo.poqtech.testrobot

import android.content.Context
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers

open class TestRobot {

    private fun context(): Context = InstrumentationRegistry.getInstrumentation().targetContext

    fun typeTextCloseKeyboard(
        @StringRes viewId: Int,
        text: String
    ): ViewInteraction =
        onView(withId(viewId)).perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

    fun clickView(@StringRes viewId: Int): ViewInteraction = onView((withId(viewId))).perform(ViewActions.click())

    private fun onViewWithId(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(resId: Int, text: String): ViewInteraction = onView(withId(resId)).check(matches(withText(text)))

    fun snackBarMessage(
        @IntegerRes viewId: Int, errorValue: String): ViewInteraction =
        onView(withId(viewId)).check(matches(withText(errorValue)))

    fun clickListViewItem(
        @StringRes listRes: Int,
        position: Int
    ): ViewInteraction = Espresso.onData(CoreMatchers.anything())
        .inAdapterView(CoreMatchers.allOf(withId(listRes)))
        .atPosition(position).perform(
            ViewActions.click()
        )

    internal fun verifyViewStatesVisibility(
        vararg viewStates: Pair<Int, ViewMatchers.Visibility>
    ) {
        for (view in viewStates) {
            onView(withId(view.first))
                .check(matches(ViewMatchers.withEffectiveVisibility(view.second)))
        }
    }

    internal fun closeKeyboard() = ViewActions.closeSoftKeyboard()
}