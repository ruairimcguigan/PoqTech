package com.demo.poqtech.data.connectivity

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject

class DefaultNetworkState @Inject constructor() : NetworkState {

    override fun hasActiveState(context: Application): Boolean {
        var hasActiveConnectivity = false
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (networkCapabilities != null) {
            if (networkCapabilities.hasTransport(TRANSPORT_ETHERNET)
                || networkCapabilities.hasTransport(TRANSPORT_WIFI)
            ) {
                hasActiveConnectivity = true
            }
        }
        return hasActiveConnectivity
    }
}