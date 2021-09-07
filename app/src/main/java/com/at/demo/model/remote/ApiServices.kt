package com.at.demo.model.remote

import com.at.demo.model.response.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET(ApiConstant.API_VIDEO_LIST)
    suspend fun getPhotoList(
        @Query("tags") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): PhotoResponse

}