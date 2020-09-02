package com.demo.poqtech.data.connectivity

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import javax.inject.Inject

class DefaultNetworkState @Inject constructor(
    private val context: Context) : NetworkState {

    override fun hasActiveState(): Boolean {
        var hasActiveConnectivity = false
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (networkCapabilities != null) {
            if (networkCapabilities.hasTransport(TRANSPORT_ETHERNET) ||
                networkCapabilities.hasTransport(TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(TRANSPORT_WIFI_AWARE)
            ) {
                hasActiveConnectivity = true
            }
        }
        return hasActiveConnectivity
    }
}