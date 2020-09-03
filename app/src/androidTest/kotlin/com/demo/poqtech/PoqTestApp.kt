package com.demo.poqtech

class PoqTestApp : PoqApp() {

    override fun getBaseUrl(): String = "http://127.0.0.1:8080"

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

    companion object {
        private var sInstance: PoqTestApp? = null

        @Synchronized
        fun getInstance(): PoqTestApp {
            return sInstance!!
        }
    }
}