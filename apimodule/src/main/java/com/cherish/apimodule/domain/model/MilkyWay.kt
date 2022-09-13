package com.cherish.apimodule.domain.model

import com.google.gson.annotations.SerializedName

data class MilkyWay(
    @SerializedName("collection")
    val collection: Collection?
)

data class Collection(
    @SerializedName("items")
    val items: List<Item>?,
)

data class Item(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("links")
    val links: List<Link>?
)

data class Link(
    @SerializedName("href")
    val href: String?,
)

data class Data(
    @SerializedName("center")
    val center: String?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("nasa_id")
    val nasaId: String?,
    @SerializedName("title")
    val title: String?
)