package com.demo.poqtech.util

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.demo.poqtech.PoqTestApp
import java.io.IOException
import java.io.InputStreamReader

object ResponseReader {

    const val CHARSET = "UTF-8"

    fun readJsonFromFile(fileName: String): String {
        try {
            val inputStream = (getInstrumentation()
                .targetContext
                .applicationContext as PoqTestApp)
                .assets.open(fileName)

            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, CHARSET)
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}