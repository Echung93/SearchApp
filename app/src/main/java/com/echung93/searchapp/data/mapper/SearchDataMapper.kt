package com.echung93.searchapp.data.mapper

import com.echung93.searchapp.data.model.KakaoDto
import com.echung93.searchapp.domain.search.SearchData
import com.echung93.searchapp.domain.search.SearchEntity

object SearchDataMapper {
    fun KakaoDto.toDomain() = SearchEntity(
        isEnd = this.meta.isEnd,
        itemList = this.documents.map { item ->
            SearchData(
                title = item.siteName,
                doc_url = item.url,
                image_url = item.imageUrl,
                thumbnail_url = item.thumbnailUrl,
                dateTime = item.datetime
            )
        }
    )
}