package com.demo.poqtech.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
    @SerializedName(value = "key")
    val key: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "nodeId")
    val node_id: String,
    @SerializedName(value = "spdxId")
    val spdx_id: String,
    @SerializedName(value = "url")
    val url: String
): Parcelable