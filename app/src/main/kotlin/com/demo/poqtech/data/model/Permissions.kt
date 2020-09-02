package com.demo.poqtech.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Permissions(
    @SerializedName(value = "admin")
    val admin: Boolean,
    @SerializedName(value = "pull")
    val pull: Boolean,
    @SerializedName(value = "push")
    val push: Boolean
): Parcelable