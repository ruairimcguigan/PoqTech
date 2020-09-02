package com.demo.poqtech.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName(value = "avatarUrl")
    val avatar_url: String,
    @SerializedName(value = "eventsUrl")
    val events_url: String,
    @SerializedName(value = "followersUrl")
    val followers_url: String,
    @SerializedName(value = "followingUrl")
    val following_url: String,
    @SerializedName(value = "gistsUrl")
    val gists_url: String,
    @SerializedName(value = "gravatarId")
    val gravatar_id: String,
    @SerializedName(value = "htmlUrl")
    val html_url: String,
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "login")
    val login: String,
    @SerializedName(value = "nodeId")
    val node_id: String,
    @SerializedName(value = "organisationsUrl")
    val organizations_url: String,
    @SerializedName(value = "receivedEventsUrl")
    val received_events_url: String,
    @SerializedName(value = "reposUrl")
    val repos_url: String,
    @SerializedName(value = "siteAdmin")
    val site_admin: Boolean,
    @SerializedName(value = "starredUrl")
    val starred_url: String,
    @SerializedName(value = "subscriptionsUrl")
    val subscriptions_url: String,
    @SerializedName(value = "type")
    val type: String,
    @SerializedName(value = "url")
    val url: String
): Parcelable