package com.demo.poqtech.data.connectivity

import android.app.Application

interface NetworkState {
    fun hasActiveState(context: Application): Boolean
}