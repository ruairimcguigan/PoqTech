package com.demo.poqtech

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?): Application =
        super.newApplication(
        classLoader,
        PoqTestApp::class.java.name,
        context
    )
}