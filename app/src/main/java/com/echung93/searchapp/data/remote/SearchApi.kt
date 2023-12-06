package com.echung93.searchapp.data.remote

import com.echung93.searchapp.BuildConfig
import com.echung93.searchapp.data.model.KakaoDto
import com.echung93.searchapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {
    @GET("/v2/search/image")
    suspend fun getSearchDataResponse(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER + BuildConfig.KAKAO_API_KEY,
        @Query("query") query: String,
        @Query("sort") sort:String,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): KakaoDto
}