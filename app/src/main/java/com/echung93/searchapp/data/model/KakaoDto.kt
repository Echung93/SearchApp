package com.echung93.searchapp.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class KakaoDto (
    @SerializedName("documents")
    val documents: List<KakaoDtoDocument>,
    @SerializedName("meta")
    val meta: KakaoDtoMeta
)

data class KakaoDtoDocument(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("datetime")
    val datetime: Date,
    @SerializedName("display_sitename")
    val siteName: String,
    @SerializedName("doc_url")
    val url: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("width")
    val width: Int
)

data class KakaoDtoMeta(
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
)
